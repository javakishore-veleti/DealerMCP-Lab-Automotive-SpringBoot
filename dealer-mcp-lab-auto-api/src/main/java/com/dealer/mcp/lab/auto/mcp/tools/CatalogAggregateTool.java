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
public class CatalogAggregateTool implements McpTool {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getName() { return "catalog.aggregate"; }
    @Override
    public String getDescription() { return "Aggregates categories, products, discounts and features."; }
    @Override
    public List<String> getSupportedContextTypes() {
        return List.of("ProductCategory","Product","ProductFeature","ProductDiscount");
    }

    @Override
    public McpToolSchema getInputSchema() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type","object");
        schema.putObject("properties").putObject("includeDiscounts").put("type","boolean");
        return new McpToolSchema("CatalogInput", schema);
    }

    @Override
    public McpToolSchema getOutputSchema() {
        ObjectNode schema = mapper.createObjectNode();
        schema.put("type","object");
        return new McpToolSchema("CatalogOutput", schema);
    }

    @Override
    public Object execute(Object input) {
        Map<String,Object> map = mapper.convertValue(input, Map.class);
        boolean includeDiscounts = Boolean.TRUE.equals(map.get("includeDiscounts"));
        return Map.of(
                "categories", List.of("Tyres","Oil","Brakes"),
                "products", List.of("AllSeasonTyre","PerformanceBrakePad"),
                "includeDiscounts", includeDiscounts
        );
    }
}