package myproject.sns.web.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.sns.domain.member.entity.ChangePasswordRequest;
import myproject.sns.domain.member.service.UserService;
import myproject.sns.global.exception.CustomException;
import myproject.sns.global.security.auth.AuthenticationService;
import myproject.sns.global.security.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static myproject.sns.global.exception.CustomErrorCode.EMAIL_DUPLICATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerByUser(@Validated @RequestBody RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new CustomException(EMAIL_DUPLICATED); // 이메일 중복 에러
        }
        authenticationService.register(request);
        return ResponseEntity.ok().build();
    }

    // 비밀번호 변경
    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
