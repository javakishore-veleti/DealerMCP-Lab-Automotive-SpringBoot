package com.dealer.mcp.lab.auto.mcp.tools;

import com.dealer.mcp.lab.auto.common.mcp.McpTool;
import com.dealer.mcp.lab.auto.common.mcp.McpToolSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Component
public class ProductFeatureTool implements McpTool {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() {
        return "ProductFeatureTool";
    }

    @Override
    public String getDescription() {
        return "Returns Product Features";
    }

    @Override
    public List<String> getSupportedContextTypes() {
        return List.of("Product", "ProductFeature");
    }

    @Override
    public McpToolSchema getInputSchema() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "object");
        schema.putObject("properties").putObject("productId").put("type", "string");
        return new McpToolSchema("FeatureInput", schema);
    }

    @Override
    public McpToolSchema getOutputSchema() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type", "array");
        return new McpToolSchema("FeatureOutput", schema);
    }

    @Override
    public Object execute(Object input) {
        Map<String,String> map = mapper.convertValue(input, Map.class);
        String pid = map.get("productId");
        return List.of(
                Map.of("feature","Eco Mode","productId",pid),
                Map.of("feature","High Grip","productId",pid)
        );
    }
}
