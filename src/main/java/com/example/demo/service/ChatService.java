package com.example.demo.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;
    
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    
    public String chat(String message) {
        return chatModel.call(message);
    }
    
    public String chatWithSystem(String userMessage, String systemPrompt) {
        Prompt prompt = new Prompt(
            java.util.List.of(
                new SystemMessage(systemPrompt),
                new UserMessage(userMessage)
            )
        );
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
