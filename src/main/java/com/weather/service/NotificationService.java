package com.weather.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final TelegramService telegramService;

    public NotificationService(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    public void sendNotification(String chatId, String message) {
        telegramService.sendNotification(chatId, message);
    }
}
