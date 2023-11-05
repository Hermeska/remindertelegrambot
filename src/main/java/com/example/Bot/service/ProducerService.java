package com.example.Bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;


public interface ProducerService {

    void produce(String rabbitQueue, Update update);
}