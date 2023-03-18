package search.blog.api.global.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter(value = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "에러 발생 정보")
public class ErrorResponseDto {

    @JsonProperty("status_code")
    @Schema(description = "에러 발생 코드")
    private Integer statusCode;

    @JsonProperty("message")
    @Schema(description = "에러 메세지")
    private String message;

    @JsonProperty("error")
    @Schema(description = "에러 상태 메세지")
    private String error;

    public ErrorResponseDto(String path, BaseException exception) {

        this(path, null, exception);
    }

    public ErrorResponseDto(BaseException ges) {

        this(null, null, ges);
    }

    public ErrorResponseDto(String path, @Nullable String message, BaseException ges) {
        this.statusCode = ges.getStatus().value();
        this.error = ges.getStatus().getReasonPhrase();
        this.message = ges.getMessage();
    }
}


