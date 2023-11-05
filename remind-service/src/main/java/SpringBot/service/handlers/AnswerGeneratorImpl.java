package SpringBot.service.handlers;

import SpringBot.entity.response.Response;
import SpringBot.service.AnswerGenerator;
import SpringBot.service.handlers.answers.ResponseHandlerImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AnswerGeneratorImpl implements AnswerGenerator {
    private final ResponseHandlerImpl responseHandler;

    @Override
    public void sendResponse(Response message) {
        if (responseHandler.supports(message)) {
            responseHandler.handle(message);
        }
    }
}