package com.dealer.mcp.lab.auto.common.mcp;

import lombok.Data;
import java.util.*;

@Data
public class McpToolRegistry {
    private final Map<String, McpTool> tools = new HashMap<>();

    public void register(McpTool tool) {
        tools.put(tool.getName(), tool);
    }

    public McpTool getTool(String name)
    {
        return tools.get(name);
    }

    public Collection<McpTool> list() {
        return tools.values();
    }

    public Collection<McpTool> listByContextType(String ctx) {
        if (ctx == null) return tools.values();
        return tools.values().stream()
                .filter(t -> t.getSupportedContextTypes().contains(ctx))
                .toList();
    }
}