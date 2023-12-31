package SpringBot.service.handlers.answers;


import SpringBot.entity.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class ResponseHandlerImpl implements ResponseHandler {
    private final DeleteMessageResponseHandler deleteMessageResponseHandler;
    private final EditMessageTextResponseHandler editMessageTextResponseHandler;
    private final SendMessageResponseHandler sendMessageResponseHandler;


    private Set<ResponseHandler> getHandlers() {
        Set<ResponseHandler> result = new HashSet<>();
        result.add(deleteMessageResponseHandler);
        result.add(editMessageTextResponseHandler);
        result.add(sendMessageResponseHandler);
        return result;
    }

    @Override
    public boolean supports(Response message) {
        return true;
    }

    @Override
    public void handle(Response message) {
        getHandlers().stream()
                .filter(handler -> handler.supports(message))
                .findFirst()
                .ifPresent(handler -> handler.handle(message));
    }
}