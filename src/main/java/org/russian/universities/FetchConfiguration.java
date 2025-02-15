package org.russian.universities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FetchConfiguration {
    @Bean
    public WebClient interfacxWebClient() {
        return WebClient.builder()
                .baseUrl("https://academia.interfax.ru")
                .defaultHeader(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01")
                .defaultHeader(HttpHeaders.ACCEPT_LANGUAGE, "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
                .defaultHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:135.0) Gecko/20100101 Firefox/135.0")
                .build();
    }
}
