package com.example.Bot.handlers;

import lombok.AllArgsConstructor;
import model.RabbitQueue;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.example.Bot.service.ProducerServiceImpl;
@Component
@AllArgsConstructor
public class CallbackHandler implements Handler {
    private final ProducerServiceImpl updateProducer;

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery();
    }

    @Override
    public void handle(Update update) {
        processCallbackQuery(update);
    }

    private void processCallbackQuery(Update update) {
        updateProducer.produce(RabbitQueue.CALLBACK_QUERY, update);
    }
}