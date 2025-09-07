package com.boardly.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class OpenAiClient {

    private final WebClient client;
    private final String model;

    public OpenAiClient(
            @Value("${ai.openai.base-url}") String baseUrl,
            @Value("${ai.openai.api-key}") String apiKey,
            @Value("${ai.openai.responses-model}") String model
    ) {
        this.model = model;
        this.client = WebClient.builder()
                .baseUrl(baseUrl) // ex.: https://api.openai.com/v1
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String responses(String system, String user) {
        Map<String, Object> body = Map.of(
                "model", model,
                "input", List.of(
                        Map.of("role", "system", "content", system),
                        Map.of("role", "user", "content", user)
                ),
                "temperature", 0.2
        );

        return client.post()
                .uri("/responses")
                .bodyValue(body)
                .exchangeToMono(this::handleWithRetryAfter)
                .timeout(Duration.ofSeconds(45))
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(2))    // 3 tentativas: 2s, 4s, 8s
                                .jitter(0.3)                          // evita rajadas simultâneas
                                .filter(t -> t instanceof TooManyRequestsException
                                        || t instanceof ServerErrorException)
                )
                .onErrorResume(e -> {
                    log.error("OpenAI call failed: {}", e.toString());
                    return Mono.just("AI error: " + e.getMessage());
                })
                .block();
    }

    private Mono<String> handleWithRetryAfter(ClientResponse resp) {
        if (resp.statusCode().is2xxSuccessful()) {
            return resp.bodyToMono(Map.class)
                    .map(map -> String.valueOf(map.getOrDefault("output_text", "")));
        }
        if (resp.statusCode().value() == 429) {
            // honra Retry-After (segundos), se presente
            Optional<String> ra = resp.headers().asHttpHeaders()
                    .getOrEmpty("Retry-After").stream().findFirst();
            Duration wait = ra.map(s -> Duration.ofSeconds(Long.parseLong(s)))
                    .orElse(Duration.ofSeconds(5));
            return Mono.delay(wait)
                    .then(Mono.error(new TooManyRequestsException("429 from OpenAI")));
        }
        if (resp.statusCode().is5xxServerError()) {
            return Mono.error(new ServerErrorException("5xx from OpenAI"));
        }
        // Outros erros (400/401/403/422…): devolve detalhe legível
        return resp.bodyToMono(String.class)
                .flatMap(body -> Mono.error(new RuntimeException(
                        "OpenAI error " + resp.statusCode().value() + ": " + body)));
    }

    static class TooManyRequestsException extends RuntimeException {
        public TooManyRequestsException(String m) { super(m); }
    }
    static class ServerErrorException extends RuntimeException {
        public ServerErrorException(String m) { super(m); }
    }
}
