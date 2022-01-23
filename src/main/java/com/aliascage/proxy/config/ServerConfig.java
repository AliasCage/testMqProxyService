package com.aliascage.proxy.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Getter
@Configuration
@ConfigurationProperties("external")
public class ServerConfig {

    private static final String TEMPLATE = "http://%s/product";

    private Map<String, String> servers;

    public void setServers(Map<String, String> servers) {
        servers.forEach((sid, url) -> servers.put(sid, String.format(TEMPLATE, url)));
        this.servers = servers;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
