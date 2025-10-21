package com.dealer.mcp.lab.auto.mcp.util;

import com.dealer.mcp.lab.auto.common.mcp.McpTool;
import com.dealer.mcp.lab.auto.common.mcp.McpToolSchema;
import com.dealer.mcp.lab.auto.common.svc.mcp.ProductContextSvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Component
public class McpContextResolveTool implements McpTool {

    private final ProductContextSvc contextService;

    public McpContextResolveTool(ProductContextSvc contextService) {
        this.contextService = contextService;
    }

    @Override
    public String getName() { return "context.resolve"; }
    @Override
    public String getDescription() { return "Resolves contextual information for a given product."; }
    @Override
    public List<String> getSupportedContextTypes() { return List.of("Product"); }

    @Override
    public McpToolSchema getInputSchema() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode schema = mapper.createObjectNode();

        schema.put("type", "object");
        schema.put("description", "Input schema for context.resolve. Requires a single entityId field.");

        // Define properties
        ObjectNode properties = schema.putObject("properties");
        ObjectNode entityId = properties.putObject("entityId");
        entityId.put("type", "string");
        entityId.put("description", "Unique identifier of the product or entity to resolve");

        // Define required fields
        schema.putArray("required").add("entityId");

        return new McpToolSchema("ContextResolveInput", schema);
    }

    @Override
    public McpToolSchema getOutputSchema() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode schema = mapper.createObjectNode();

        schema.put("type", "object");

        ObjectNode properties = schema.putObject("properties");
        properties.putObject("id").put("type", "string");
        properties.putObject("name").put("type", "string");
        properties.putObject("price").put("type", "number");
        properties.putObject("category").put("type", "string");

        ObjectNode features = properties.putObject("features");
        features.put("type", "array");
        features.putObject("items").put("type", "object");

        ObjectNode discounts = properties.putObject("discounts");
        discounts.put("type", "array");
        discounts.putObject("items").put("type", "object");

        schema.put("description", "Output schema for context.resolve. Returns full product context with features and discounts.");

        return new McpToolSchema("ContextResolveOutput", schema);
    }

    @Override
    public Object execute(Object input) {
        Map<String, String> map = new ObjectMapper().convertValue(input, Map.class);
        String productId = map.get("entityId");
        return contextService.buildProductContext(productId);
    }
}