package myproject.sns.domain.member.service;

import lombok.RequiredArgsConstructor;
import myproject.sns.domain.member.dao.UserRepository;
import myproject.sns.domain.member.entity.ChangePasswordRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        // TODO : 비밀번호 변경
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
