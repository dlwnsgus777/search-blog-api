package search.blog.api.domain.search.modules.kakao;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class KakaoWebClient {

   @Value("${kakao.rest-api-key}")
   private String restKey;

   @Value("${kakao.api-prefix}")
   private String apiPrefix;

   private final static String TOKEN_HEADER = "Authorization";

   private ExchangeStrategies exchangeStrategies =  ExchangeStrategies.builder()
         .codecs(configure -> configure.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)).build();

   private HttpClient httpClient = HttpClient.create()
         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
         .responseTimeout(Duration.ofSeconds(1))
         .doOnConnected(conn -> conn
               .addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
               .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
         ).resolver(DefaultAddressResolverGroup.INSTANCE);

   private final WebClient webClient = WebClient.builder() //httpClient
         .exchangeStrategies(exchangeStrategies)
         .clientConnector(new ReactorClientHttpConnector())
         .defaultHeader(TOKEN_HEADER, getKakaoSearchApiKey())
         .clientConnector(new ReactorClientHttpConnector(httpClient))
         .baseUrl("https://dapi.kakao.com/v2/search/web")
         .build();

   private String getKakaoSearchApiKey() {
      return apiPrefix + " " + restKey;
   }

   public WebClient getWebClient() {
      return webClient;
   }
}
