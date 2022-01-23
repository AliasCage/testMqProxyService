package com.aliascage.proxy.controller;

import com.aliascage.proxy.model.Product;
import com.aliascage.proxy.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product) {
        service.asyncSend(product);
    }
}
