package com.example.Bot.handlers;

import lombok.AllArgsConstructor;
import model.RabbitQueue;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.example.Bot.service.ProducerServiceImpl;


@Component
@AllArgsConstructor
public class MessageHandler implements Handler {
    private final ProducerServiceImpl updateProducer;

    @Override
    public boolean supports(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    @Override
    public void handle(Update update) {
        processTextMessage(update);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(RabbitQueue.TEXT_MESSAGE_UPDATE, update);
    }
}