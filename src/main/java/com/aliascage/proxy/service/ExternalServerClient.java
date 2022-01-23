package com.aliascage.proxy.service;

import com.aliascage.proxy.model.Product;

public interface ExternalServerClient {
    /**
     * Передача продукта на внешний сервис по указанному хосту
     *
     * @param host    - адрес внешнего сервиса
     * @param product - сообщения с продуктом
     * @return В случае ошибки или отсутствия доступа к сервису возвращается FALSE
     */
    boolean post(String host, Product product);
}
