package com.example.Bot.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.*;

@Configuration
public class RabbitConfiguration {
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue textMessageUpdateQueue() {
        return new Queue(RabbitQueue.TEXT_MESSAGE_UPDATE);
    }

    @Bean
    public Queue answerMessageQueue() {
        return new Queue(RabbitQueue.RESPONSE_MESSAGE);
    }

    @Bean
    public Queue callbackQueryQueue() {
        return new Queue(RabbitQueue.CALLBACK_QUERY);
    }

    @Bean
    public Queue editAnswerMessageQueue() {
        return new Queue(RabbitQueue.EDIT_RESPONSE_MESSAGE);
    }

    @Bean
    public Queue deleteAnswerMessage() {
        return new Queue(RabbitQueue.DELETE_RESPONSE_MASSAGE);
    }
}
