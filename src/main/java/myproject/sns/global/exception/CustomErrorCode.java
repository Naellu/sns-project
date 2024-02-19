package myproject.sns.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    EMAIL_DUPLICATED(400, "이미 등록된 이메일입니다");

    private final int status;
    private final String message;

}
