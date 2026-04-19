package com.example.demo.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {
    private final ChatModel chatModel;
    
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    
    public String chat(String message) {
        return chatModel.call(message);
    }
    
    public Flux<String> chatStream(String message) {
        return chatModel.stream(message);
    }
    
    /**
     * 调用模型，包含系统提示和用户消息
     * @param userMessage 用户消息
     * @param systemPrompt 系统提示消息
     * @param systemPrompt 系统提示消息
     */
    public String chatWithSystem(String userMessage, String systemPrompt) {
        System.out.println("userMessage: d" + userMessage);
        Prompt prompt = new Prompt(
            java.util.List.of(
                new SystemMessage(systemPrompt),
                new UserMessage(userMessage)
            )
        );
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
