package myproject.sns.web.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.sns.domain.member.entity.ChangePasswordRequest;
import myproject.sns.domain.member.service.UserService;
import myproject.sns.global.security.auth.AuthenticationRequest;
import myproject.sns.global.security.auth.AuthenticationResponse;
import myproject.sns.global.security.auth.AuthenticationService;
import myproject.sns.global.security.auth.RegisterRequest;
import myproject.sns.util.SnowflakeGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerByUser(@RequestBody RegisterRequest request) {
        SnowflakeGenerator idGenerator = new SnowflakeGenerator();
        request.setId(idGenerator.nextId());
        AuthenticationResponse registeredData = authenticationService.register(request);
        return ResponseEntity.ok(registeredData);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    // 비밀번호 변경
    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
