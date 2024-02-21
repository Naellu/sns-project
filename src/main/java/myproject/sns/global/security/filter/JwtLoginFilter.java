package myproject.sns.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.sns.global.security.dto.LoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

//    private final JwtService jwtService;

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
        // jwt 토큰 발급 및 반환
        log.info("로그인 성공");
        log.info("[successfulAuthentication] Authentication : {}", authResult);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    // 로그인 실패 시 동작
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 로그인 페이지로 돌아가게 하기
        log.info("로그인 실패");
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
