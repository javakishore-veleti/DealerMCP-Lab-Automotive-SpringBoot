package com.dealer.mcp.lab.auto.mcp.api;

import com.dealer.mcp.lab.auto.common.dto.mcp.McpToolInfo;
import com.dealer.mcp.lab.auto.common.dto.mcp.McpToolResp;
import com.dealer.mcp.lab.auto.common.svc.mcp.McpExecutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "McpServer", description = "McpServer")
@RestController
@RequestMapping("/dealer-mcp/server/v1")
public class McpServerController {

    private final McpExecutor executor;

    public McpServerController(McpExecutor executor) { this.executor = executor; }

    @GetMapping("/tools")
    public ResponseEntity<McpToolResp> listTools(@RequestParam(required = false) String contextType) {
        List<McpToolInfo> toolInfos = executor.getRegistry()
                .listByContextType(contextType)
                .stream()
                .map(t -> new McpToolInfo(
                        t.getName(),
                        t.getDescription(),
                        t.getSupportedContextTypes(),
                        t.getInputSchema(),
                        t.getOutputSchema()
                ))
                .toList();

        return ResponseEntity.ok(new McpToolResp(toolInfos));

    }

    @Operation(summary = "Execute MCP Tool", description = "Executes a registered MCP tool by name")
    @PostMapping("/execute/{tool}")
    public ResponseEntity<String> execute(@PathVariable String tool, @RequestBody String json) throws Exception {
        return ResponseEntity.ok(executor.execute(tool, json));
    }
}