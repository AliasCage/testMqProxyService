package com.aliascage.proxy.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(@Value("${rmq.host}") String host) {
        return new CachingConnectionFactory(host);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connection) {
        return new RabbitTemplate(connection);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connection, ServerConfig config) {
        final RabbitAdmin admin = new RabbitAdmin(connection);
        config.getServers().keySet()
                .stream()
                .map(Queue::new)
                .forEach(admin::declareQueue);
        return admin;
    }

}
