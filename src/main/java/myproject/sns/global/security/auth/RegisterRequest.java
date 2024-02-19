package myproject.sns.global.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.sns.domain.member.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long id;

    @NotBlank(message = "이름 입력은 필수입니다")
    private String name;

    @NotBlank(message = "이메일 입력은 필수입니다")
    @Email
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다")
    @Size(min = 5, max = 50, message = "비밀번호는 5~50자 길이만 허용합니다")
    private String password;

    private Role role;
}
