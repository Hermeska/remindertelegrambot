package SpringBot.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


/**
 * Ответ в случае, когда не удалось распознать текст напоминания.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UnrecognizedReminderResponse implements SendableResponse {
    private String chatId;
    private static final String response = "I can't recognize it, please try again";

    @Override
    public SendMessage getSendMessage() {
        return SendMessage.builder()
                .chatId(chatId)
                .text(response)
                .parseMode(ParseMode.HTML)
                .build();
    }
}
