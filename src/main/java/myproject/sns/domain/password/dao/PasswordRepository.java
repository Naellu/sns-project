package myproject.sns.domain.password.dao;

import myproject.sns.domain.password.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
    Optional<Password> findByUserId(long userId);
}
