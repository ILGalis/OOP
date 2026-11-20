package ru.nsu.fit.oop.ilg.recordbook.core;

/**
 * Дисциплина в зачетной книжке.
 */
public class Subject {
    private final String name;
    private final boolean isExam;

    /**
     * Создает дисциплину.
     *
     * @param name название
     * @param isExam является ли экзаменом
     */
    public Subject(String name, boolean isExam) {
        this.name = name;
        this.isExam = isExam;
    }

    /**
     * Возвращает название дисциплины.
     *
     * @return название
     */
    public String getSubjectName() {
        return name;
    }

    /**
     * Проверяет, является ли дисциплина экзаменом.
     *
     * @return true, если экзамен
     */
    public boolean isExam() {
        return isExam;
    }
}