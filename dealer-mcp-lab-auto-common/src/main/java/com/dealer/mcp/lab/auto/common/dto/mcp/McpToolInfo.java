package com.dealer.mcp.lab.auto.common.dto.mcp;

import com.dealer.mcp.lab.auto.common.mcp.McpToolSchema;

import java.util.List;

public record McpToolInfo(
        String name,
        String description,
        List<String> supportedContextTypes,
        McpToolSchema inputSchema,
        McpToolSchema outputSchema
) {}