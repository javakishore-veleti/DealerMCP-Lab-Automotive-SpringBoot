package com.dealer.mcp.lab.auto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI dealerMcpOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DealerMCP Automotive API")
                        .description("OpenAPI 3 specification for DealerMCP-Lab-Automotive-SpringBoot project")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Development Server"),
                        new Server().url("https://dealer-mcp-api.dev.company.com").description("Dev Server")
                ));
    }
}