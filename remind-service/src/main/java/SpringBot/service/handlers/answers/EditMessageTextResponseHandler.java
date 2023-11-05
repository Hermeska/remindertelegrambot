package SpringBot.service.handlers.answers;

import SpringBot.entity.response.EditableResponse;
import SpringBot.entity.response.Response;
import SpringBot.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@AllArgsConstructor
public class EditMessageTextResponseHandler implements ResponseHandler {
    private final ProducerService producerService;

    @Override
    public boolean supports(Response response) {
        return response instanceof EditableResponse;
    }

    @Override
    public void handle(Response response) {
        EditableResponse editableResponse = (EditableResponse) response;
        EditMessageText message = editableResponse.getEditMessageText();
        producerService.produceEditMessageText(message);
    }
}

