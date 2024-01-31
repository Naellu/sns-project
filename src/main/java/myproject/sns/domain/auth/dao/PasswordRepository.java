package myproject.sns.domain.auth.dao;

import myproject.sns.domain.auth.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Integer> {

}
