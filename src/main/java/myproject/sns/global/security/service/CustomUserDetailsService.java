package myproject.sns.global.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.sns.domain.member.dao.UserRepository;
import myproject.sns.domain.member.entity.User;
import myproject.sns.domain.password.dao.PasswordRepository;
import myproject.sns.domain.password.entity.Password;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.springframework.security.core.userdetails.User.builder;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("[CustomUserDetailsService] loadUserByUsername() : 존재하지 않는 사용자입니다."));
        Password password = passwordRepository.findByUserId(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("[CustomUserDetailsService] loadUserByUsername() : 비밀번호가 일치하지 않습니다."));

        return builder() // UserDetails.User의 builder
                .username(user.getUsername())
                .password(password.getPassword())
                .authorities(getAuthorities(user))
                .build();
    }

    // 권한 조회 및 반환
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getAuthorities();
    }
}
