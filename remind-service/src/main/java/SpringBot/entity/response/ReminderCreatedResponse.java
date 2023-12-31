package SpringBot.entity.response;

import SpringBot.entity.Reminder;
import SpringBot.entity.enums.Emoji;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.format.DateTimeFormatter;

/**
 * Ответ, подтверждающий успешное создание напоминания.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReminderCreatedResponse implements SendableResponse {
    private String chatId;
    private Reminder reminder;

    @Override
    public SendMessage getSendMessage() {
        return SendMessage.builder()
                .chatId(chatId)
                .text(generateReminderCreatedAnswerText())
                .parseMode(ParseMode.HTML)
                .build();
    }

    private String generateReminderCreatedAnswerText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String sendTimeFormatted = reminder.getSendTime().format(formatter);
        String dayOfWeek = switch (reminder.getSendTime().getDayOfWeek()) {
            case MONDAY -> "(Mon)";
            case TUESDAY -> "(Tue)";
            case WEDNESDAY -> "(Wed)";
            case THURSDAY -> "(Thu)";
            case FRIDAY -> "(Fri)";
            case SATURDAY -> "(Sat)";
            default -> "(Sun)";
        };

        return String.format("%s Reminder created\n\n%s <b>%s %s</b>\n%s %s",
                Emoji.PUSHPIN.get(),
                Emoji.CLOCK.get(),
                sendTimeFormatted,
                dayOfWeek,
                Emoji.ARROW.get(),
                reminder.getMessage());
    }
}
