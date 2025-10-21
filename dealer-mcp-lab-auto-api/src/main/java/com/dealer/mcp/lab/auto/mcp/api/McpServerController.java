package com.dealer.mcp.lab.auto.mcp.api;

import com.dealer.mcp.lab.auto.common.mcp.McpTool;
import com.dealer.mcp.lab.auto.common.svc.mcp.McpExecutor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "McpServer", description = "McpServer")
@RestController
@RequestMapping("/dealer-mcp/server/v1")
public class McpServerController {

    private final McpExecutor executor;

    public McpServerController(McpExecutor executor) { this.executor = executor; }

    @GetMapping("/tools")
    public Collection<McpTool> listTools(@RequestParam(required = false) String contextType) {
        return executor.getRegistry().listByContextType(contextType);
    }

    @PostMapping("/execute/{tool}")
    public String execute(@PathVariable String tool, @RequestBody String json) throws Exception {
        return executor.execute(tool, json);
    }
}