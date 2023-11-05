package SpringBot.service.handlers.answers;

import SpringBot.entity.response.DeletableResponse;
import SpringBot.entity.response.Response;
import SpringBot.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

@Component
@AllArgsConstructor
public class DeleteMessageResponseHandler implements ResponseHandler {
    private final ProducerService producerService;

    @Override
    public boolean supports(Response response) {
        return response instanceof DeletableResponse;
    }

    @Override
    public void handle(Response response) {
        DeletableResponse deletableResponse = (DeletableResponse) response;
        DeleteMessage message = deletableResponse.getDeleteMessage();
        producerService.produceDeleteResponseMessage(message);
    }
}
