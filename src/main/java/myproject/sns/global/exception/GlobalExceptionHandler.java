package myproject.sns.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 커스텀 예외 처리
     * 1. CustomException에 상수 저장
     * 2. 저장된 상수 값을 가지고 CustomErrorCode에서 name, message 추출
     * 3. 에러 로깅
     * 4. 커스텀 에러 이름과 메시지를 가진 ErrorResult 객체 반환
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResult> handleCustomException(CustomException ex) {
        CustomErrorCode errorCode = ex.getErrorCode();
        ErrorResult errorResult = new ErrorResult(errorCode.name(), errorCode.getMessage());
        log.error("[GlobalExceptionHandler] errorCode: {}, message: {}", errorCode.name(), errorCode.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.valueOf(errorCode.getStatus()));
    }

    // 검증 실패 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResult> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getAllErrors().get(0).getDefaultMessage();
        ErrorResult errorResult = new ErrorResult("VALIDATION_ERROR", errorMessage);
        log.error("[GlobalExceptionHandler] errorName: MethodArgumentNotValidException, message: {}", errorMessage);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResult> handleNotFoundExceptions(NoHandlerFoundException ex) {
        String errorMessage = "잘못된 요청 경로입니다.";
        ErrorResult errorResult = new ErrorResult("NOT_FOUND", errorMessage);
        log.error("[GlobalExceptionHandler] errorName: NoHandlerFoundException, message: {}", errorMessage);
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }
}
