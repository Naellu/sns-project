package myproject.sns.global.security.dao;

import myproject.sns.global.security.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value =
      " select t from Token t inner join User u " +
      " on t.user = u.name " +
      " where u.id = :id and (t.expired = false or t.revoked = false) "
      )
    List<Token> findAllValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);
}
