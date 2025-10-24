package com.dealer.mcp.lab.auto.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Service
public class McpClientService {

    private final WebClient webClient;

    @Value("${mcp.server.url}")
    private String mcpServerUrl;

    public McpClientService(WebClient.Builder webClientBuilder) {
        log.info("STARTED MCP Client Service");
        this.webClient = webClientBuilder.build();
    }

    public JsonNode resolveProductContext(String productId) {
        log.info("STARTED Invoking resolveProductContext /api/v1/productContext/resolve");

        JsonNode jsonNode = webClient.post()
                .uri(mcpServerUrl + "/api/v1/productContext/resolve")
                .bodyValue(Map.of("productId", productId))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        log.info("COMPLETED Invoking resolveProductContext /api/v1/productContext/resolve");
        return jsonNode;
    }

    public JsonNode recommendCrossSell(String dealerId, String productId) {
        log.info("STARTED Invoking recommendCrossSell /api/v1/context/dealerProduct/recommend");

        JsonNode jsonNode = webClient.post()
                .uri(mcpServerUrl + "/api/v1/context/dealerProduct/recommend")
                .bodyValue(Map.of("dealerId", dealerId, "productId", productId))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        log.info("COMPLETED Invoking recommendCrossSell /api/v1/context/dealerProduct/recommend");
        return jsonNode;
    }
}
