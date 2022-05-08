package gft.api.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConsumerApplication {

	/**
	 * Application consumes PoliticianAPI.
	 * @param builder
	 * @return
	 */
	@Bean
	public WebClient webClientPolitician(WebClient.Builder builder) {
		return builder
				.baseUrl("http://localhost:8080")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .exchangeStrategies(ExchangeStrategies.builder()
						    .codecs(configurer -> configurer
						      .defaultCodecs()
						      .maxInMemorySize(16 * 1024 * 1024))
						    .build())
				.build();
	}
	
}
