package search.blog.api.global.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter(value = AccessLevel.PUBLIC)
public class BasePageResponseDto {

    @Schema(description = "전체 개수", example = "99999", defaultValue = "0", required = true)
    @JsonProperty("total")
    @Builder.Default
    protected long total = 0;

    @Schema(description = "조회된 개수", example = "20", defaultValue = "0", required = true)
    @JsonProperty("count")
    @Builder.Default
    protected long count = 0;

    @Schema(description = "현재 페이지", example = "1", defaultValue = "1", required = true)
    @JsonProperty("page")
    @Builder.Default
    protected long currentPage = 1;

    @Schema(description = "페이지 크기", example = "20", defaultValue = "null")
    @JsonProperty("page_size")
    protected Integer pageSize;

    @Schema(description = "다음 페이지", example = "null", defaultValue = "null")
    @JsonProperty("next_page")
    protected Long nextPage;

    public Long getNextPage() {
        if (nextPage != null) {
            return nextPage;
        }

        if (total - count <= 0) {
            return null;
        }
        return this.count < (long)this.pageSize ? null : this.currentPage + 1L;
    };
}

