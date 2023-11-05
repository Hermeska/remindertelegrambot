package com.example.Bot.service;


import com.example.Bot.handlers.UpdateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import model.RabbitQueue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;



@Service
@Log4j
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final UpdateHandler updateHandler;

    @Override
    @RabbitListener(queues = RabbitQueue.RESPONSE_MESSAGE)
    public void consumeResponseMessage(SendMessage sendMessage) {
        log.info("Received SendMessage: " + sendMessage);
        updateHandler.setView(sendMessage);
    }

    @Override
    @RabbitListener(queues = RabbitQueue.EDIT_RESPONSE_MESSAGE)
    public void consumeEditResponseMessage(EditMessageText editMessageText) {
        log.info("Received EditMessageText: " + editMessageText);
        updateHandler.setView(editMessageText);
    }

    @Override
    @RabbitListener(queues = RabbitQueue.DELETE_RESPONSE_MASSAGE)
    public void consumeDeleteResponseMessage(DeleteMessage deleteMessage) {
        log.info("Received DeleteMessage: " + deleteMessage);
        updateHandler.setView(deleteMessage);
    }
}
