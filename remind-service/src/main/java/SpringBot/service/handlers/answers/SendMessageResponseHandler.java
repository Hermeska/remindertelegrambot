package SpringBot.service.handlers.answers;

import SpringBot.entity.response.Response;
import SpringBot.entity.response.SendableResponse;
import SpringBot.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@AllArgsConstructor
public class SendMessageResponseHandler implements ResponseHandler {
    private final ProducerService producerService;

    @Override
    public boolean supports(Response response) {
        return response instanceof SendableResponse;
    }

    @Override
    public void handle(Response response) {
        SendableResponse sendableResponse = (SendableResponse) response;
        SendMessage message = sendableResponse.getSendMessage();
        producerService.produceSendMessage(message);
    }
}
