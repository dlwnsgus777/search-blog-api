package search.blog.api.domain.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import static org.mockito.Mockito.when;

@WebMvcTest(SearchController.class)
class SearchControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private SearchService searchService;

   private WebTestClient webTestClient;

   @BeforeEach
   public void setup() {
      this.webTestClient = MockMvcWebTestClient
            .bindTo(mockMvc)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .build();
   }

   @Test
   public void 블로그_검색_테스트_실패_검색어_미입력() {

      this.webTestClient.get().uri("/api/search")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
            .expectStatus().isBadRequest()
            .expectBody().jsonPath("$.message")
            .isEqualTo("검색어를 입력해 주세요.");
   }

   @Test
   public void 블로그_검색_테스트_성공() {

      this.webTestClient.get().uri(uriBuilder ->
               uriBuilder.path("/api/search")
                  .queryParam("query", "TEST")
                  .queryParam("page", 1)
                  .queryParam("size", 12)
                  .build()
            )
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
            .expectStatus().isOk()
            .expectBody();
   }
}