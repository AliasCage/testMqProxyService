package com.aliascage.proxy.service;

import com.aliascage.proxy.model.Product;

public interface ProductService {
    /**
     * Асинхронная обработка сообщения с продуктом
     * Продукт передаётся во все внешние сервисы, в случае неудачи
     * если случилась внутренняя ошибка на сервисе или сервис недоступен,
     * то продукт помещается в очередь
     *
     * @param product - сообщение с продуктом
     */
    void asyncSend(Product product);
}
