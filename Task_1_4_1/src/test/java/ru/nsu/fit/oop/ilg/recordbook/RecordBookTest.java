package ru.nsu.fit.oop.ilg.recordbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.recordbook.core.Grade;
import ru.nsu.fit.oop.ilg.recordbook.core.RecordBook;
import ru.nsu.fit.oop.ilg.recordbook.core.Semester;
import ru.nsu.fit.oop.ilg.recordbook.core.Subject;

/**
 * Тесты для электронной зачетной книжки.
 */
class RecordBookTest {

    private RecordBook recordBook;
    private Subject mathExam;
    private Subject physicsExam;
    private Subject historyDiff;

    @BeforeEach
    void setUp() {
        recordBook = new RecordBook(false);
        mathExam = new Subject("Математика", true);
        physicsExam = new Subject("Физика", true);
        historyDiff = new Subject("История", false);
    }

    /**
     * Проверяет средний балл для пустой зачетки.
     */
    @Test
    void shouldReturnZeroForEmptyRecordBook() {
        assertEquals(0.0, recordBook.getAverageGrade());
    }

    /**
     * Проверяет расчет среднего балла.
     */
    @Test
    void shouldCalculateAverageGrade() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.GOOD);
        s1.addGrade(historyDiff, Grade.DIFF_PASS);

        Semester s2 = new Semester(2);
        s2.addGrade(historyDiff, Grade.GOOD);

        recordBook.addSemester(s1);
        recordBook.addSemester(s2);

        assertEquals(4.33, recordBook.getAverageGrade(), 0.01);
    }

    /**
     * Проверяет, что бюджетник не может перевестись.
     */
    @Test
    void studentOnBudgetCannotTransferToBudget() {
        assertFalse(recordBook.canTransferToBudget());
    }

    /**
     * Проверяет перевод, если нет троек в последних 2 сессиях.
     */
    @Test
    void shouldTransferIfNoSatisfactoryExamsInLastTwoSemesters() {
        RecordBook paidBook = new RecordBook(true);

        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.ENOUGH);

        Semester s2 = new Semester(2);
        s2.addGrade(mathExam, Grade.GOOD);

        Semester s3 = new Semester(3);
        s3.addGrade(physicsExam, Grade.EXCELLENT);
        s3.addGrade(historyDiff, Grade.ENOUGH);

        paidBook.addSemester(s1);
        paidBook.addSemester(s2);
        paidBook.addSemester(s3);

        assertTrue(paidBook.canTransferToBudget());
    }

    /**
     * Проверяет отказ в переводе при тройке в последней сессии.
     */
    @Test
    void shouldNotTransferIfSatisfactoryExamInLastSemester() {
        RecordBook paidBook = new RecordBook(true);

        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.GOOD);

        Semester s2 = new Semester(2);
        s2.addGrade(physicsExam, Grade.ENOUGH);

        paidBook.addSemester(s1);
        paidBook.addSemester(s2);

        assertFalse(paidBook.canTransferToBudget());
    }

    /**
     * Проверяет отсутствие красного диплома при тройке.
     */
    @Test
    void shouldNotGetRedDiplomaIfHasSatisfactory() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.ENOUGH);
        recordBook.addSemester(s1);

        assertFalse(recordBook.canGetRedDiploma());
    }

    /**
     * Проверяет отсутствие красного диплома при менее 75% отлично.
     */
    @Test
    void shouldNotGetRedDiplomaIfLessThan75PercentExcellent() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.EXCELLENT);
        s1.addGrade(historyDiff, Grade.EXCELLENT);
        s1.addGrade(new Subject("Химия", true), Grade.GOOD);
        s1.addGrade(new Subject("Биология", true), Grade.GOOD);

        recordBook.addSemester(s1);
        recordBook.setDiplomWorkGrade(Grade.EXCELLENT);

        assertFalse(recordBook.canGetRedDiploma());
    }

    /**
     * Проверяет получение красного диплома при 75% отлично.
     */
    @Test
    void shouldGetRedDiplomaIf75PercentAndNoSatisfactory() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.EXCELLENT);
        s1.addGrade(historyDiff, Grade.EXCELLENT);
        s1.addGrade(new Subject("Химия", true), Grade.EXCELLENT);
        s1.addGrade(new Subject("Биология", true), Grade.GOOD);

        recordBook.addSemester(s1);
        recordBook.setDiplomWorkGrade(Grade.EXCELLENT);

        assertTrue(recordBook.canGetRedDiploma());
    }

    /**
     * Проверяет отсутствие красного диплома при плохой ВКР.
     */
    @Test
    void shouldNotGetRedDiplomaIfDiplomaWorkIsNotExcellent() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        recordBook.addSemester(s1);

        recordBook.setDiplomWorkGrade(Grade.GOOD);

        assertFalse(recordBook.canGetRedDiploma());
    }

    /**
     * Проверяет прогноз красного диплома до защиты ВКР.
     */
    @Test
    void shouldPredictRedDiplomaBeforeDiplomaWork() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        recordBook.addSemester(s1);

        assertTrue(recordBook.canGetRedDiploma());
    }

    /**
     * Проверяет отсутствие стипендии при пустой зачетке.
     */
    @Test
    void shouldNotGetScholarshipIfEmpty() {
        assertFalse(recordBook.canGetIncreasedScholarship());
    }

    /**
     * Проверяет получение стипендии при хороших оценках.
     */
    @Test
    void shouldGetScholarshipIfOnlyGoodAndExcellent() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.GOOD);
        recordBook.addSemester(s1);

        assertTrue(recordBook.canGetIncreasedScholarship());
    }

    /**
     * Проверяет отсутствие стипендии при тройке в текущем семестре.
     */
    @Test
    void shouldNotGetScholarshipIfHasSatisfactoryInCurrentSemester() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.GOOD);

        Semester s2 = new Semester(2);
        s2.addGrade(physicsExam, Grade.ENOUGH);

        recordBook.addSemester(s1);
        recordBook.addSemester(s2);

        assertFalse(recordBook.canGetIncreasedScholarship());
    }

    /**
     * Проверяет отсутствие стипендии при незачете.
     */
    @Test
    void shouldNotGetScholarshipIfHasDiffFailInCurrentSemester() {
        Semester s1 = new Semester(1);
        s1.addGrade(historyDiff, Grade.DIFF_FAIL);
        recordBook.addSemester(s1);

        assertFalse(recordBook.canGetIncreasedScholarship());
    }
}