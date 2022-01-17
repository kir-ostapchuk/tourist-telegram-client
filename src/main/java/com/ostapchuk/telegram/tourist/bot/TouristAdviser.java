package com.ostapchuk.telegram.tourist.bot;

import com.ostapchuk.telegram.tourist.api.TouristClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TouristAdviser extends TelegramLongPollingBot {

    private final TouristClient touristClient;

    @Autowired
    public TouristAdviser(final TouristClient touristClient) {
        this.touristClient = touristClient;
    }

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        final SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId().toString());
        if (update.getMessage().getText().startsWith("/")) {
            msg.setText("Welcome to Tourist Adviser!");
        } else {
            msg.setText(touristClient.findMessageByCityName(update.getMessage().getText()));
        }
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}