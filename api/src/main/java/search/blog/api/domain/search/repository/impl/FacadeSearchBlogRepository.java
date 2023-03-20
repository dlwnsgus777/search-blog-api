package search.blog.api.domain.search.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.SpringProperties;
import org.springframework.stereotype.Repository;
import search.blog.api.domain.search.modules.kakao.KaKaoBlogSearchApi;
import search.blog.api.domain.search.repository.SearchBlogRepository;

@Repository
@RequiredArgsConstructor
public class FacadeSearchBlogRepository implements SearchBlogRepository {

   private final KaKaoBlogSearchApi kaKaoBlogSearchApi;


}
