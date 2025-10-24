package com.dealer.mcp.lab.auto.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class McpClientService {

    private final WebClient webClient;

    @Value("${mcp.server.url}")
    private String mcpServerUrl;

    public McpClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public JsonNode resolveProductContext(String productId) {
        return webClient.post()
                .uri(mcpServerUrl + "/api/v1/productContext/resolve")
                .bodyValue(Map.of("productId", productId))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

    public JsonNode recommendCrossSell(String dealerId, String productId) {
        return webClient.post()
                .uri(mcpServerUrl + "/api/v1/context/dealerProduct/recommend")
                .bodyValue(Map.of("dealerId", dealerId, "productId", productId))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }
}
