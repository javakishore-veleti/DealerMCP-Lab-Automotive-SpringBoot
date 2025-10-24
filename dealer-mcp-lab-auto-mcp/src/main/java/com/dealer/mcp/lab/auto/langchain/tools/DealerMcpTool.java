package com.dealer.mcp.lab.auto.langchain.tools;

import com.dealer.mcp.lab.auto.service.McpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import dev.langchain4j.agent.tool.Tool;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DealerMcpTool {

    private final McpClientService mcpClientService;

    public DealerMcpTool(McpClientService mcpClientService) {
        this.mcpClientService = mcpClientService;
    }

    //@Tool("Resolve product context by productId for intelligent Q&A")
    @Tool("Use this when the user asks about a specific product’s details or attributes.")
    public String resolveProductContext(String productId) {
        System.out.println("🔥 DealerMcpTool.resolveProductContext invoked with productId=" + productId);
        JsonNode node = mcpClientService.resolveProductContext(productId);
        return node.toPrettyString();
    }

    //@Tool("Recommend cross-sell products based on dealer and product context. Dealer ID optional.")
    @Tool("Use this tool only when the dealerId is known. If dealerId is not specified, do not call this tool. Use this ONLY when the user explicitly asks for cross-sell or upsell recommendations for a product.")
    /*
    @Tool("""
Recommend cross-sell products for a given productId.
Use this tool only when dealerId is known; otherwise, leave dealerId blank.
""")
*/
    public String recommendCrossSell(@Nullable String dealerId, String productId) {
        System.out.println("🔥 DealerMcpTool.recommendCrossSell invoked with dealerId=" + dealerId + ", productId=" + productId);
        JsonNode node = mcpClientService.recommendCrossSell(dealerId, productId);
        return node.toPrettyString();
    }
}
