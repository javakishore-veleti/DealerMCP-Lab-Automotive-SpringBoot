package com.dealer.mcp.lab.auto.langchain.service;

import com.dealer.mcp.lab.auto.langchain.tools.DealerMcpTool;
import com.dealer.mcp.lab.auto.service.Intent;
import com.dealer.mcp.lab.auto.service.IntentRouter;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiProductAdvisor {

    private final ChatModel chatModel;
    private final DealerMcpTool dealerMcpTool;
    private final IntentRouter intentRouter;

    public String answerProductQuery(String userQuery) {
        Intent intent = intentRouter.detectIntent(userQuery);
        System.out.println("🧭 Detected intent: " + intent);

        return switch (intent) {
            case CROSS_SELL -> handleCrossSell(userQuery);
            case PRODUCT_CONTEXT -> dealerMcpTool.resolveProductContext(extractProductId(userQuery));
            case PRICING -> handlePricing(userQuery);
            default -> fallbackToAssistant(userQuery);
        };
    }

    private String handleCrossSell(String query) {
        String productId = extractProductId(query);
        String dealerId = extractDealerId(query);
        if (dealerId == null) {
            // fallback automatically
            return dealerMcpTool.resolveProductContext(productId);
        }
        return dealerMcpTool.recommendCrossSell(dealerId, productId);
    }

    private String fallbackToAssistant(String query) {
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .tools(dealerMcpTool)
                .build();
        return assistant.chat(query);
    }

    private String handlePricing(String query) {
        String productId = extractProductId(query);
        if (productId == null) {
            return "⚠️ Could not detect a product ID in your pricing query.";
        }
        return dealerMcpTool.getPricing(productId);
    }

    private String extractProductId(String query) {
        if (query == null) return null;
        var matcher = java.util.regex.Pattern.compile("\\bP\\d+\\b", java.util.regex.Pattern.CASE_INSENSITIVE)
                .matcher(query);
        return matcher.find() ? matcher.group().toUpperCase() : null;
    }

    private String extractDealerId(String query) {
        if (query == null) return null;
        var matcher = java.util.regex.Pattern.compile("\\bD\\d+\\b", java.util.regex.Pattern.CASE_INSENSITIVE)
                .matcher(query);
        return matcher.find() ? matcher.group().toUpperCase() : null;
    }

    interface Assistant {
        //@SystemMessage("You are an expert product advisor helping dealers interpret catalog data.")
       /* @SystemMessage("""
You are a product advisor. Use only one MCP tool per query:
- If user asks for recommendations → use recommendCrossSell only.
- If user asks for product details → use resolveProductContext only.
""")
        */
        @SystemMessage("""
You are a dealer product assistant.
- Only call 'recommendCrossSell' when both dealerId and productId are available.
- Otherwise, call 'resolveProductContext'.
- For product details: call resolveProductContext.
- For recommendations: call recommendCrossSell.
If dealerId is not provided, leave it blank.

You are also a product advisor.
- If the query involves cross-sell recommendations and dealerId is missing, do NOT call recommendCrossSell.
- Instead, call resolveProductContext to show general product insights.
""")
        String chat(String query);
    }
}
