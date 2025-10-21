package com.dealer.mcp.lab.auto.common.dto.mcp;

import java.time.Instant;
import java.util.List;

public record McpToolResp(
        String version,
        Instant timestamp,
        List<McpToolInfo> tools
) {
    public McpToolResp(List<McpToolInfo> tools) {
        this("1.0.0", Instant.now(), tools);
    }
}