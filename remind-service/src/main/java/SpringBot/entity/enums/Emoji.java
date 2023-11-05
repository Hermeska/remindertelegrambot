package SpringBot.entity.enums;

/**
 * Перечисление, определяющее типы эможди.
 */
public enum Emoji {
    CLOCK("\uD83D\uDD5D"),
    PUSHPIN("\uD83D\uDCC5"),
    DATE("\uD83D\uDCEA"),
    ARROW("↪"),
    EXCLAMATION("❗"),
    ZAP("\u26A1");

    private final String value;

    Emoji(String value) {
        this.value = value;
    }

    /**
     * Возвращает Unicode для отправки эмоджи пользователю.
     */
    public String get() {
        return value;
    }
}
