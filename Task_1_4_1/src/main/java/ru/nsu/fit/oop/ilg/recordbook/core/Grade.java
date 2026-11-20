package ru.nsu.fit.oop.ilg.recordbook.core;

/**
 * Оценка в зачетной книжке.
 */
public enum Grade {
    EXCELLENT(5),
    GOOD(4),
    ENOUGH(3),
    BAD(2),
    DIFF_FAIL(0),
    DIFF_PASS(0);

    private final int value;

    /**
     * Создает оценку.
     *
     * @param value числовое значение
     */
    Grade(int value) {
        this.value = value;
    }

    /**
     * Возвращает числовое значение.
     *
     * @return значение
     */
    public int getValue() {
        return value;
    }

    /**
     * Влияет ли оценка на средний балл.
     *
     * @return true, если влияет
     */
    public boolean isCountsOnAverage() {
        return this != DIFF_FAIL && this != DIFF_PASS;
    }
}