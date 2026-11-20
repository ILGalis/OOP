package ru.nsu.fit.oop.ilg.recordbook.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Семестр с оценками.
 */
public class Semester {
    private final int semesterNumber;
    private final Map<Subject, Grade> grades;

    /**
     * Создает семестр.
     *
     * @param semesterNumber номер семестра
     */
    public Semester(int semesterNumber) {
        this.semesterNumber = semesterNumber;
        this.grades = new HashMap<>();
    }

    /**
     * Добавляет оценку.
     *
     * @param subject дисциплина
     * @param grade   оценка
     */
    public void addGrade(Subject subject, Grade grade) {
        grades.put(subject, grade);
    }

    /**
     * Возвращает номер семестра.
     *
     * @return номер
     */
    public int getSemesterNumber() {
        return semesterNumber;
    }

    /**
     * Возвращает оценки семестра.
     *
     * @return оценки
     */
    public Map<Subject, Grade> getGrades() {
        return grades;
    }
}