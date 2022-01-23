package com.aliascage.proxy.service;

import com.aliascage.proxy.config.ServerConfig;
import com.aliascage.proxy.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class WatchServiceImpl implements WatchService {

    private AmqpTemplate template;
    private AmqpAdmin admin;
    private ServerConfig config;
    private ExternalServerClient client;

    @Override
    @Scheduled(fixedDelayString = "${watch.delay}")
    public void tryResendMessages() {
        config.getServers().forEach((sid, url) -> {
            var messageCount = Optional.ofNullable(admin.getQueueInfo(sid))
                    .map(QueueInformation::getMessageCount)
                    .orElseThrow();
            if (messageCount == 0) {
                return;
            }

            log.info("Queue '{}' has {} message", sid, messageCount);
            Product product = (Product) template.receiveAndConvert(sid);
            while (product != null) {
                var sended = client.post(url, product);
                if (!sended) {
                    template.convertAndSend(sid, product);
                    return;
                }
                product = (Product) template.receiveAndConvert(sid);
            }
        });
    }
}
