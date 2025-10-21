package com.dealer.mcp.lab.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "com.dealer.mcp.lab.auto.common.entity.rdbms"
})
@EnableJpaRepositories(basePackages = {
        "com.dealer.mcp.lab.auto.common.dao.rdbms"
})
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}
