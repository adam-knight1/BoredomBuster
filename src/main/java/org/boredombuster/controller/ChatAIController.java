package org.boredombuster.controller;

import org.boredombuster.dto.ChatMessageDTO;
import org.boredombuster.service.ChatAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ChatAIController {

    ChatAIService chatAIService;

    public ResponseEntity<String> getChatResponse(String prompt) {
        String response = chatAIService.getResponseFromAI(prompt);
        return ResponseEntity.ok(response);
    }
}
