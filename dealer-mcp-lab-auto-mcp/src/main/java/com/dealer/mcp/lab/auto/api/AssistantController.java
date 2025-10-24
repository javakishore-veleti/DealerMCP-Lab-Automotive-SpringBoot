package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.langchain.service.AiProductAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/assistant")
@RequiredArgsConstructor
public class AssistantController {

    private final AiProductAdvisor advisor;

    @PostMapping("/query")
    public ResponseEntity<Map<String, Object>> query(@RequestBody Map<String, String> body) {
        String query = body.get("query");
        String answer = advisor.answerProductQuery(query);
        return ResponseEntity.ok(Map.of("answer", answer));
    }
}