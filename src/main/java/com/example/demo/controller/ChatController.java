package com.example.demo.controller;

import com.example.demo.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestBody ChatRequest request) {
        return chatService.chatStream(request.message());
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
