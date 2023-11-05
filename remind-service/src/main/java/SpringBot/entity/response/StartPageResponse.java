package SpringBot.entity.response;

import SpringBot.entity.enums.Emoji;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


/**
 * Ответ на команду /start.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class StartPageResponse implements SendableResponse {
    private final String START_SERVICE_COMMAND_ANSWER_TEXT = String.format("""
            %s 
            Привет, я умный бот, который поможет тебе никогда не забывать свои дела!
            Мой создатель @Undef1nedbehavioR
            Попробуй сформулировать напоминнание, например: 
            13-30 обед
            18 сентября день рождения!
            позвони человеку через 20 минут.
             Лучше пиши на английском, я лучше понимаю.
             Введи /list , чтобы увидеть или удалить напоминания.
            """, Emoji.DATE.get());

    private String chatId;

    @Override
    public SendMessage getSendMessage() {
        return SendMessage.builder()
                .chatId(chatId)
                .text(START_SERVICE_COMMAND_ANSWER_TEXT)
                .parseMode(ParseMode.HTML)
                .build();
    }
}
