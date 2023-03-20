package search.blog.api.domain.search.modules.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import search.blog.api.domain.search.dto.SearchBlogRequest;

@Component
@RequiredArgsConstructor
public class KaKaoBlogSearchApi {

   private final KakaoWebClient kakaoWebClient;


   public Object searchBlog(SearchBlogRequest dto) {
      return
   }
}
