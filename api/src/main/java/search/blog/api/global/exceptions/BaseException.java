package search.blog.api.global.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter(value = AccessLevel.PUBLIC)
public class BaseException extends RuntimeException {
    private HttpStatus status;
    private LocalDateTime time;

    public BaseException(HttpStatus status) {
        this(status, "오류가 발생했습니다.");
    }

    public BaseException(Exception exception) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    public <T extends Exception> BaseException(HttpStatus status, T exception) {
        super(exception);

        this.status = status;
        this.time = LocalDateTime.now();
    }

    public BaseException(HttpStatus status, @Nullable String errorMessage) {
        super(Optional.ofNullable(errorMessage).orElse(""));

        this.status = status;
        this.time = LocalDateTime.now();
    }

}
