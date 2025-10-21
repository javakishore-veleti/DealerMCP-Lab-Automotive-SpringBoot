package com.dealer.mcp.lab.auto.mcp.config;

import com.dealer.mcp.lab.auto.common.mcp.McpToolRegistry;
import com.dealer.mcp.lab.auto.common.svc.mcp.McpExecutor;
import com.dealer.mcp.lab.auto.mcp.tools.CatalogAggregateTool;
import com.dealer.mcp.lab.auto.mcp.tools.ProductDiscountTool;
import com.dealer.mcp.lab.auto.mcp.tools.ProductFeatureTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

    @Bean
    public McpToolRegistry mcpToolRegistry(
            ProductFeatureTool featureTool,
            ProductDiscountTool discountTool,
            CatalogAggregateTool catalogTool) {

        McpToolRegistry reg = new McpToolRegistry();
        reg.register(featureTool);
        reg.register(discountTool);
        reg.register(catalogTool);
        return reg;
    }

    @Bean
    public McpExecutor mcpExecutor(McpToolRegistry reg) {
        return new McpExecutor(reg);
    }
}
