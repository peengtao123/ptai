package com.example.demo.controller;

import com.example.demo.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatService.chat(request.message());
    }

    @PostMapping("/with-system")
    public String chatWithSystem(@RequestBody ChatWithSystemRequest request) {
        return chatService.chatWithSystem(
            request.userMessage(),
            request.systemPrompt()
        );
    }

    public record ChatRequest(String message) {}

    public record ChatWithSystemRequest(String userMessage, String systemPrompt) {}
}
