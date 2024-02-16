package myproject.sns.domain.password.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "auth_test", name = "password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "password_id")
    private Long id; // 인조 pk

    @Column
    private String password; // 로그인에 사용되는 비밀번호

    @Column(name = "user_id")
    private Long userId; // 유저 인조키 pk
}
