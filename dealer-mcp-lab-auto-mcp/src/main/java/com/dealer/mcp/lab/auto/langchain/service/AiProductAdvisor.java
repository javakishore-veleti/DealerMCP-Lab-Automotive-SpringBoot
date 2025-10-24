package com.dealer.mcp.lab.auto.langchain.service;

import com.dealer.mcp.lab.auto.langchain.tools.DealerMcpTool;
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

    public String answerProductQuery(String query) {
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .tools(dealerMcpTool)
                .build();

        return assistant.chat(query);
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
""")
        String chat(String query);
    }
}
