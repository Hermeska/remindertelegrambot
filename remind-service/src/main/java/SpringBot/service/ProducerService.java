package SpringBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface ProducerService {
    void produceSendMessage(SendMessage sendMessage);

    void produceEditMessageText(EditMessageText editMessageText);

    void produceDeleteResponseMessage(DeleteMessage deleteMessage);
}
