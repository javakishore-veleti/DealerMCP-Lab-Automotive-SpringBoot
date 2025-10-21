package com.dealer.mcp.lab.auto.common.mcp;

import com.fasterxml.jackson.databind.JsonNode;

public record McpToolSchema(String name, JsonNode schema) { }