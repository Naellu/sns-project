package myproject.sns.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.sns.domain.member.entity.User;
import myproject.sns.global.security.auth.AuthenticationResponse;
import myproject.sns.global.security.auth.AuthenticationService;
import myproject.sns.global.security.dto.LoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationService authService;

    // 로그인 요청 시 들어있는 사용자 정보로 인증 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 요청에서 이름과 비밀번호 추출(본문에서 추출)
        try (ServletInputStream inputStream = request.getInputStream()) {
            LoginRequest loginRequest = new ObjectMapper().readValue(inputStream, LoginRequest.class);
            log.info("[로그인] 사용자 이름 : {}, 비밀번호 : {}", loginRequest.getUsername(), loginRequest.getPassword());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            log.info("token : {}", authenticationToken);

            return getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 로그인 성공 시 인증
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthenticationResponse authResponse = authService.IssuingJwtTokens(authResult.getName());
        log.info("AuthenticationResponse : {}", authResponse);
//        response.setHeader("Authorization", "Bearer " + authResponse.getAccessToken());
        Cookie refreshTokenCookie = new Cookie("Refresh", authResponse.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true); // httpOnly 설정
        refreshTokenCookie.setSecure(true); // HTTPS 프로토콜만 허용
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 만료 기간

        response.addCookie(refreshTokenCookie);
    }

    // 로그인 실패 시 동작
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 로그인 페이지로 돌아가게 하기
        log.info("로그인 실패");
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
