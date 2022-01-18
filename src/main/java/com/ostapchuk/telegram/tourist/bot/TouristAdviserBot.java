package com.ostapchuk.telegram.tourist.bot;

import com.ostapchuk.telegram.tourist.api.TouristClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.ostapchuk.telegram.tourist.util.Const.MSG_WELCOME;
import static com.ostapchuk.telegram.tourist.util.Const.SLASH;

@Component
@RequiredArgsConstructor
@Slf4j
public class TouristAdviserBot extends TelegramLongPollingBot {

    private final TouristClient touristClient;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        final SendMessage msg = createMessage(update);
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            log.error("Telegram API exception: {}", e.getMessage());
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    private SendMessage createMessage(final Update update) {
        final SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId().toString());
        final String text = update.getMessage().getText();
        if (text.startsWith(SLASH)) {
            msg.setText(MSG_WELCOME);
        } else {
            msg.setText(touristClient.findMessageByCityName(text));
        }
        return msg;
    }
}
