package com.weather.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

@Component
public class TelegramService extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            Long chatId = message.getChatId();

            if (text.startsWith("/")) {
                handleCommands(chatId, text);
            }
        } else if (message != null && message.hasPhoto()) {
            handlePhotoMessage(message);
        }
    }

    private void handleCommands(Long chatId, String command) {
        switch (command) {
            case "/start" -> sendNotification(chatId.toString(), "Hello! I am a weather bot.");
            case "/help" -> sendNotification(chatId.toString(), "This is a bot for getting weather information.");
            default -> sendNotification(chatId.toString(), "Unknown command.");
        }
    }

    private void handlePhotoMessage(Message message) {
        PhotoSize photo = message.getPhoto().get(0);
        String fileId = photo.getFileId();
        sendNotification(message.getChatId().toString(), "Photo received! File ID: " + fileId);
    }

    public void sendNotification(String chatId, String messageText) {
        SendMessage message = new SendMessage(chatId, messageText);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPhotoNotification(String chatId, InputFile photoUrl) {
        SendPhoto sendPhoto = new SendPhoto(chatId, photoUrl);
        try {
            execute(sendPhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
