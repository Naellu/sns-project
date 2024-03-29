package myproject.sns.global.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.sns.domain.member.dao.UserRepository;
import myproject.sns.domain.member.entity.User;
import myproject.sns.domain.password.dao.PasswordRepository;
import myproject.sns.domain.password.entity.Password;
import myproject.sns.global.security.dao.TokenRepository;
import myproject.sns.global.security.service.JwtService;
import myproject.sns.global.security.token.Token;
import myproject.sns.global.security.token.TokenType;
import myproject.sns.util.SnowflakeGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SnowflakeGenerator idGenerator;

    @Transactional
    public void register(RegisterRequest request) {
        long newId = idGenerator.nextId(); // snowflake로 생성된 id값
        User user = User.builder()
                .id(newId)
                .name(request.getName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        Password password = Password.builder()
                .userId(newId)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        passwordRepository.save(password);
    }

    public AuthenticationResponse IssuingJwtTokens(String username) {
        User user = userRepository.findByName(username).orElseThrow();
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // 기존 토큰 강제 만료
        revokeAllUserTokens(user);
        // 리프레쉬 토큰 저장
        saveUsersRefreshToken(user, refreshToken);

        // 액세스 토큰, 리프레쉬 토큰 값을 가지는 AuthenticationResponse 반환
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUsersRefreshToken(User user, String refreshToken) {
        Token token = Token.builder()
                .user(user)
                .token(refreshToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
                AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
