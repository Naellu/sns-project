package myproject.sns.domain.member.service;

import lombok.RequiredArgsConstructor;
import myproject.sns.domain.member.entity.ChangePasswordRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface UserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
