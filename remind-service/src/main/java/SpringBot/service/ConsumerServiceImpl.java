package SpringBot.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static model.RabbitQueue.CALLBACK_QUERY;
import static model.RabbitQueue.TEXT_MESSAGE_UPDATE;

@Service
@Log4j
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final ReminderServiceImpl mainService;

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessageUpdate(Update update) {
        log.info("Received Update: " + update);
        mainService.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = CALLBACK_QUERY)
    public void consumeCallbackQuery(Update update) {
        log.info("Received Update: " + update);
        mainService.processCallbackQuery(update);
    }
}