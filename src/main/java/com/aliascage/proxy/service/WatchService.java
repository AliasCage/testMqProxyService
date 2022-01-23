package com.aliascage.proxy.service;

public interface WatchService {
    /**
     * Мониторинг очередей и попытка дослать сообщения, которые не удалось отправить
     */
    void tryResendMessages();
}
