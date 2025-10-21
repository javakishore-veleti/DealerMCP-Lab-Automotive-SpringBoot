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
public class ProductDiscountTool implements McpTool {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() { return "discount.evaluate"; }
    @Override
    public String getDescription() { return "Calculates effective price after discounts."; }
    @Override
    public List<String> getSupportedContextTypes() { return List.of("Product","ProductDiscount"); }

    @Override
    public McpToolSchema getInputSchema() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type","object");
        schema.putObject("properties").putObject("productId").put("type","string");
        schema.putObject("properties").putObject("basePrice").put("type","number");
        return new McpToolSchema("DiscountInput", schema);
    }

    @Override
    public McpToolSchema getOutputSchema() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type","object");
        schema.putObject("properties").putObject("finalPrice").put("type","number");
        return new McpToolSchema("DiscountOutput", schema);
    }

    @Override
    public Object execute(Object input) {
        Map<String,Object> map = mapper.convertValue(input, Map.class);
        double base = ((Number)map.getOrDefault("basePrice",100)).doubleValue();
        double discount = 10.0; // fake demo value
        return Map.of("finalPrice", base*(1-discount/100));
    }
}