package myproject.sns.domain.password.dao;

import myproject.sns.domain.password.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Integer> {

}
