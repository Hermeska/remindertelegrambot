package SpringBot.entity.response;

import SpringBot.entity.Reminder;
import SpringBot.entity.enums.Emoji;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Ответ с информацией о напоминании.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReminderResponse implements SendableResponse {
    private String chatId;
    private Reminder reminder;

    public ReminderResponse(Reminder reminder) {
        this.reminder = reminder;
        this.chatId = reminder.getAppUser().getChatId().toString();
    }

    @Override
    public SendMessage getSendMessage() {
        return SendMessage.builder()
                .chatId(chatId)
                .text(Emoji.EXCLAMATION.get() + "   " + reminder.getMessage())
                .parseMode(ParseMode.HTML)
                .build();
    }
}
