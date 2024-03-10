package org.boredombuster.controller;

import org.boredombuster.dto.ChatMessageDTO;
import org.boredombuster.service.ChatAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
public class ChatAIController {

    @Autowired
    ChatAIService chatAIService;

    public ChatAIController(ChatAIService chatAIService) {
        this.chatAIService = chatAIService;
    }
    @PostMapping("/ask")
    public Mono<ResponseEntity<String>> getChatResponse(@RequestBody ChatMessageDTO chatMessageDTO) {
        return chatAIService.getResponseFromAI(chatMessageDTO.getMessage())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().body("request failed"));
    }
}
