package SpringBot.service;


import org.telegram.telegrambots.meta.api.objects.Update;
public interface ConsumerService {
    void consumeTextMessageUpdate(Update update);

    void consumeCallbackQuery(Update update);
}
