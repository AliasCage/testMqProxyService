package com.aliascage.proxy.service;

import com.aliascage.proxy.config.ServerConfig;
import com.aliascage.proxy.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private AmqpTemplate template;
    private ServerConfig config;
    private ExternalServerClient client;

    @Async
    @Override
    public void asyncSend(Product product) {
        config.getServers().forEach((sid, host) -> {
            var sended = client.post(host, product);
            if (!sended) {
                template.convertAndSend(sid, product);
            }
        });
    }
}
