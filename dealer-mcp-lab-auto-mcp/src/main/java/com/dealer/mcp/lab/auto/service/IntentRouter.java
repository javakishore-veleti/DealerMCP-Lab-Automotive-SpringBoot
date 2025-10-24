package com.dealer.mcp.lab.auto.service;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class IntentRouter {

    private final ChatModel chatModel;

    public IntentRouter(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public Intent detectIntent(String query) {
        String prompt = """
                Classify the following user request into one intent:
                - PRODUCT_CONTEXT  → user wants product details
                - CROSS_SELL       → user wants recommendations
                - PRICING          → user wants price info
                - UNKNOWN          → anything else
                Query: "%s"
                Return only one token.
                """.formatted(query);

        try {
            // ✅ 1. Build a chat message
            ChatMessage user = UserMessage.from(prompt);

            // ✅ 2. Use the new chat() API (this replaced generate())
            var response = chatModel.chat(user);

            // ✅ 3. Extract plain text
            String result = response.aiMessage().text().trim().toUpperCase();

            return Intent.valueOf(result);
        } catch (Exception e) {
            System.out.println("⚠️ IntentRouter fallback to UNKNOWN: " + e.getMessage());
            return Intent.UNKNOWN;
        }
    }
}