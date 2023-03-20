package search.blog.api.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "블로그 검색 요청")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PRIVATE)
public class SearchBlogRequest {

   @Schema(name = "검색어")
   @NotBlank(message = "검색어를 입력해 주세요.")
   private String query;

   @Schema(name = "페이지")
   private Long page;

   @Schema(name = "사이즈")
   private Long size;

}
