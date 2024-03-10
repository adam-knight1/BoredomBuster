package org.boredombuster.service;

import org.springframework.web.reactive.function.client.WebClient;

public class ChatAIService {
    private String apiKey = System.getenv("OPENAI_API_KEY");

    private final WebClient webClient;

    public ChatAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public String getResponseFromAI(String prompt){

        return "";

    }
}
