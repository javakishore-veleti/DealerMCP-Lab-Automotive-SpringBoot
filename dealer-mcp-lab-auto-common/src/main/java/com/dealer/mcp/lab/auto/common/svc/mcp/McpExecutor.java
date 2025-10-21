package com.dealer.mcp.lab.auto.common.svc.mcp;

import com.dealer.mcp.lab.auto.common.mcp.McpTool;
import com.dealer.mcp.lab.auto.common.mcp.McpToolRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class McpExecutor {
    private final McpToolRegistry registry;
    private final ObjectMapper mapper = new ObjectMapper();

    public McpExecutor(McpToolRegistry registry) { this.registry = registry; }

    public String execute(String toolName, String jsonInput) throws Exception {
        McpTool tool = registry.getTool(toolName);
        if (tool == null) throw new IllegalArgumentException("Tool not found: " + toolName);
        Object input = mapper.readValue(jsonInput, Object.class);
        Object output = tool.execute(input);
        return mapper.writeValueAsString(output);
    }
}