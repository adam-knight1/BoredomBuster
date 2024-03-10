package org.boredombuster.service;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ChatAIService {
    private String apiKey = System.getenv("OPENAI_API_KEY");

    private final WebClient webClient;

    public ChatAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public Mono<String> getResponseFromAI(String prompt) {
        return this.webClient.post()
                .uri("/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Error calling the OpenAI API");
    }
}
