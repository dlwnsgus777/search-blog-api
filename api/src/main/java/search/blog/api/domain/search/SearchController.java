package search.blog.api.domain.search;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.blog.api.domain.search.dto.SearchBlogRequest;
import search.blog.api.domain.search.dto.SearchBlogResponse;

import javax.validation.Valid;

@Tags({
      @Tag(name = "블로그 검색 Api", description = "블로그 검색 관련 api")
})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

   private final SearchService searchService;

   @GetMapping("/search")
   public SearchBlogResponse searchBlog(@Valid @ParameterObject SearchBlogRequest dto) {
      return searchService.searchBlog(dto);
   }
}
