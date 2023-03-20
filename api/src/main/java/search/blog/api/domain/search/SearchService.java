package search.blog.api.domain.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import search.blog.api.domain.search.dto.SearchBlogRequest;
import search.blog.api.domain.search.dto.SearchBlogResponse;
import search.blog.api.domain.search.repository.SearchBlogRepository;

@Service
@RequiredArgsConstructor
public class SearchService {

   private final SearchBlogRepository facadeSearchBlogRepository;

   public SearchBlogResponse searchBlog(SearchBlogRequest dto) {
      return null;
   }
}
