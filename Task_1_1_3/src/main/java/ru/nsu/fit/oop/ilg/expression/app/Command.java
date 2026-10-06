package ru.nsu.fit.oop.ilg.expression.app;

/**
 * Команды приложения.
 */
public enum Command {
    PRINT,
    DERIVATIVE,
    EVAL,
    UNKNOWN;

    /**
     * Находит команду по имени.
     *
     * @param name имя команды
     * @return команда
     */
    public static Command fromString(String name) {
        for (Command command : values()) {
            if (command.name().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}