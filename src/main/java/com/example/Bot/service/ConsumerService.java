package com.example.Bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;


public interface ConsumerService {

    void consumeResponseMessage(SendMessage sendMessage);

    void consumeEditResponseMessage(EditMessageText editMessageText);

    void consumeDeleteResponseMessage(DeleteMessage deleteMessage);
}