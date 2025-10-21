package com.dealer.mcp.lab.auto.common.svc.mcp;

import com.dealer.mcp.lab.auto.common.mcp.McpTool;
import com.dealer.mcp.lab.auto.common.mcp.McpToolRegistry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.Data;

import java.util.Set;

@Data
public class McpExecutor {
    private final McpToolRegistry registry;
    private final ObjectMapper mapper = new ObjectMapper();

    public McpExecutor(McpToolRegistry registry) { this.registry = registry; }

    private void validateInput(McpTool tool, String jsonInput) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode inputNode = mapper.readTree(jsonInput);
            JsonNode schemaNode = tool.getInputSchema().schema();

            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaNode);

            Set<ValidationMessage> errors = schema.validate(inputNode);
            if (!errors.isEmpty()) {
                throw new IllegalArgumentException("Invalid input for tool " + tool.getName() + ": " + errors);
            }
        } catch (Exception e) {
            throw new RuntimeException("Validation failed for tool " + tool.getName(), e);
        }
    }

    public String execute(String toolName, String jsonInput) throws Exception {
        McpTool tool = registry.getTool(toolName);
        if (tool == null)  {
            throw new IllegalArgumentException("Tool not found: " + toolName);
        }

        validateInput(tool, jsonInput);

        Object input = mapper.readValue(jsonInput, Object.class);
        Object output = tool.execute(input);

        return mapper.writeValueAsString(output);
    }
}