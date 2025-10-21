package com.dealer.mcp.lab.auto.common.dto.mcp;
import java.util.List;
import java.util.Map;

public record McpContextNode(
        String type,
        String id,
        Map<String,Object> attributes,
        List<McpContextLink> links
) {}

