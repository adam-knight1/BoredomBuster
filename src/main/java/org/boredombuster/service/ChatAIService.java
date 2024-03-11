package org.boredombuster.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class ChatAIService {
    private String apiKey = System.getenv("OPENAI_API_KEY");

    private final WebClient webClient;

    public ChatAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public Mono<String> getResponseFromAI(String prompt) {

        String modelEndpoint = "/chat/completions";

        String requestBody = String.format(
                "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                prompt
        );

        return this.webClient.post()
                .uri(modelEndpoint)
                .header("Authorization", "Bearer " + apiKey)
                //.header(, this.)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Error calling the OpenAI API");
    }
}
