package com.aliascage.proxy.service;

import com.aliascage.proxy.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ExternalServerClientImpl implements ExternalServerClient {

    private RestTemplate template;

    @Override
    public boolean post(String host, Product product) {
        try {
            ResponseEntity<Product> response = template.postForEntity(host, product, Product.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpServerErrorException | ResourceAccessException e) {
            return false;
        }
    }
}
