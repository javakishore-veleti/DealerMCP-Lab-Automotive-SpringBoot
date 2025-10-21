package com.dealer.mcp.lab.auto.common.mcp;

import java.util.List;

public interface McpTool {
    String getName();
    String getDescription();
    List<String> getSupportedContextTypes();
    McpToolSchema getInputSchema();
    McpToolSchema getOutputSchema();
    Object execute(Object input) throws Exception;
}
