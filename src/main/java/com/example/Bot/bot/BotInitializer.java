package com.example.Bot.bot;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Log4j
@Component
@AllArgsConstructor
public class BotInitializer {
    private final TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot((LongPollingBot) bot);
            log.info("The bot has been successfully registered.");
        } catch (TelegramApiException e) {
            log.error("Error to register bot: " + e.getMessage());
        }
    }
}