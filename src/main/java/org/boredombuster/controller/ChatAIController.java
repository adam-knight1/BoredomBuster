package org.boredombuster.controller;

import org.boredombuster.dto.ChatMessageDTO;
import org.boredombuster.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
public class ChatAIController {
    @Autowired
    OpenAIService openAIService;

    public ChatAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }
    @PostMapping("/ask")
    public Mono<ResponseEntity<String>> getChatResponse(@RequestBody ChatMessageDTO chatMessageDTO) {
        return openAIService.getResponseFromAI(chatMessageDTO.getMessage())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().body("request failed"));
    }
}
