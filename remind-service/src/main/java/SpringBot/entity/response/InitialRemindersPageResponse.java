package SpringBot.entity.response;

import SpringBot.entity.Reminder;
import SpringBot.entity.enums.Action;
import SpringBot.entity.enums.Emoji;
import SpringBot.entity.utils.CallbackData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Ответ, содержащий список первой страницы активных напоминаний пользователя.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class InitialRemindersPageResponse implements SendableResponse {
    private String chatId;
    private List<Reminder> pageReminders;
    private boolean hasNextPageReminders;

    @Override
    public SendMessage getSendMessage() {
        if (pageReminders.isEmpty()) {
            String text = Emoji.ZAP.get() + " No active reminders.";
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .parseMode(ParseMode.HTML)
                    .build();
        }
        String text = generateReminderPageAnswerText();
        InlineKeyboardMarkup markupInLine = generateInlineKeyboardMarkup();
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode(ParseMode.HTML)
                .replyMarkup(markupInLine)
                .build();
    }

    private InlineKeyboardMarkup generateInlineKeyboardMarkup() {
        List<InlineKeyboardButton> paginationLine = new ArrayList<>();
        List<InlineKeyboardButton> removeLine = new ArrayList<>();
        if (hasNextPageReminders) {
            paginationLine.add(InlineKeyboardButton.builder()
                    .text(">")
                    .callbackData(CallbackData.builder()
                            .action(Action.PAGE)
                            .page(2)
                            .build()
                            .toString())
                    .build());
        }
        InlineKeyboardButton removeButton = new InlineKeyboardButton();
        removeButton.setText("Select an item to remove");
        removeButton.setCallbackData(CallbackData.builder()
                .action(Action.PAGE_WITH_REMOVAL)
                .page(1)
                .build()
                .toString());
        removeLine.add(removeButton);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowsInLine.add(paginationLine);
        rowsInLine.add(removeLine);
        markupInLine.setKeyboard(rowsInLine);
        return markupInLine;
    }

    public String generateReminderPageAnswerText() {
        StringBuilder sb = new StringBuilder();
        sb.append(Emoji.ZAP.get())
                .append(" Active reminders")
                .append("\n\n");
        int pageSize = 10;
        int startIndex = 1;
        for (int i = 0; i < pageReminders.size(); i++) {

            Reminder reminder = pageReminders.get(i);
            String sendTimeFormatted = reminder.getSendTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String dayOfWeek = switch (reminder.getSendTime().getDayOfWeek()) {
                case MONDAY -> "(Mon)";
                case TUESDAY -> "(Tue)";
                case WEDNESDAY -> "(Wed)";
                case THURSDAY -> "(Thu)";
                case FRIDAY -> "(Fri)";
                case SATURDAY -> "(Sat)";
                default -> "(Sun)";
            };

            String s = String.format("%s <b>%s %s</b>\n%s %s",
                    Emoji.CLOCK.get(),
                    sendTimeFormatted,
                    dayOfWeek,
                    Emoji.ARROW.get(),
                    reminder.getMessage());

            int reminderNumber = startIndex + i;
            sb.append(reminderNumber).append(") ").append(s).append("\n\n");
        }
        return sb.toString();
    }
}
