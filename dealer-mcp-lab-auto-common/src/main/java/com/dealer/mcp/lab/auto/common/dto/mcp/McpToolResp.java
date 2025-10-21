package com.dealer.mcp.lab.auto.common.dto.mcp;

import java.util.List;

public record McpToolResp(
        List<McpToolInfo> tools
) {}