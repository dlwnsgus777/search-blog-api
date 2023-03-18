package search.blog.api.global.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponseDto> handleCommonException(HttpServletRequest request, BaseException exception) {
        printErrorLog(request, exception);

        exception.printStackTrace();

        ErrorResponseDto dto = new ErrorResponseDto(request.getRequestURI(), exception);

        return ResponseEntity.status(dto.getStatusCode()).body(dto);
    }

    private void printErrorLog(HttpServletRequest request, BaseException exception) {
        log.error("Error : {}, Request : {}", exception.getCause(), request.getRequestURI());
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ErrorResponseDto> handleBindException(HttpServletRequest request, BindException exception) {

        if (exception.getFieldError().getDefaultMessage() != null) {
            return handleCommonException(request, new BaseException(HttpStatus.BAD_REQUEST, Objects.requireNonNull(exception.getFieldError().getDefaultMessage())));
        }

        return handleCommonException(request, new BaseException(HttpStatus.BAD_REQUEST, Objects.requireNonNull(exception.getFieldError()).getField() + " 값을 잘못 입력하셨습니다."));
    }

}


