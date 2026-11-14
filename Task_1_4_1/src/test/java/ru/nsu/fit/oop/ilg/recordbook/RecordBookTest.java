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
        recordBook = new RecordBook(false); // По умолчанию бюджет
        mathExam = new Subject("Математика", true); // Экзамен
        physicsExam = new Subject("Физика", true); // Экзамен
        historyDiff = new Subject("История", false); // Диф. зачет
    }

    // ==================== ТЕСТЫ СРЕДНЕГО БАЛЛА ====================

    @Test
    void shouldReturnZeroForEmptyRecordBook() {
        assertEquals(0.0, recordBook.getAverageGrade());
    }

    @Test
    void shouldCalculateAverageGrade() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT); // 5
        s1.addGrade(physicsExam, Grade.GOOD);    // 4
        s1.addGrade(historyDiff, Grade.DIFF_PASS); // Не влияет на средний балл (0)

        Semester s2 = new Semester(2);
        s2.addGrade(historyDiff, Grade.GOOD); // 4

        recordBook.addSemester(s1);
        recordBook.addSemester(s2);

        // (5 + 4 + 4) / 3 = 4.333...
        assertEquals(4.33, recordBook.getAverageGrade(), 0.01);
    }

    // ==================== ТЕСТЫ ПЕРЕВОДА НА БЮДЖЕТ ====================

    @Test
    void studentOnBudgetCannotTransferToBudget() {
        RecordBook budgetBook = new RecordBook(false);
        assertFalse(budgetBook.canTransferToBudget());
    }

    @Test
    void shouldTransferIfNoSatisfactoryExamsInLastTwoSemesters() {
        RecordBook paidBook = new RecordBook(true);

        // 1 семестр (старый) - можно даже тройку, если он не входит в последние 2
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.ENOUGH);

        // 2 и 3 семестры (последние 2) - без троек по экзаменам
        Semester s2 = new Semester(2);
        s2.addGrade(mathExam, Grade.GOOD);

        Semester s3 = new Semester(3);
        s3.addGrade(physicsExam, Grade.EXCELLENT);
        s3.addGrade(historyDiff, Grade.ENOUGH); // Тройка по диф.зачету допустима!

        paidBook.addSemester(s1);
        paidBook.addSemester(s2);
        paidBook.addSemester(s3);

        assertTrue(paidBook.canTransferToBudget());
    }

    @Test
    void shouldNotTransferIfSatisfactoryExamInLastSemester() {
        RecordBook paidBook = new RecordBook(true);

        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.GOOD);

        Semester s2 = new Semester(2);
        s2.addGrade(physicsExam, Grade.ENOUGH); // Тройка на экзамене в последней сессии!

        paidBook.addSemester(s1);
        paidBook.addSemester(s2);

        assertFalse(paidBook.canTransferToBudget());
    }

    // ==================== ТЕСТЫ КРАСНОГО ДИПЛОМА ====================

    @Test
    void shouldNotGetRedDiplomaIfHasSatisfactory() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.ENOUGH); // Тройка
        recordBook.addSemester(s1);

        assertFalse(recordBook.canGetRedDiploma());
    }

    @Test
    void shouldNotGetRedDiplomaIfLessThan75PercentExcellent() {
        Semester s1 = new Semester(1);
        // 3 отлично, 2 хорошо -> 60% отлично (меньше 75%)
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.EXCELLENT);
        s1.addGrade(historyDiff, Grade.EXCELLENT);
        s1.addGrade(new Subject("Химия", true), Grade.GOOD);
        s1.addGrade(new Subject("Биология", true), Grade.GOOD);

        recordBook.addSemester(s1);
        recordBook.setDiplomWorkGrade(Grade.EXCELLENT);

        assertFalse(recordBook.canGetRedDiploma());
    }

    @Test
    void shouldGetRedDiplomaIf75PercentAndNoSatisfactory() {
        Semester s1 = new Semester(1);
        // 4 отлично, 1 хорошо -> 80% отлично
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.EXCELLENT);
        s1.addGrade(historyDiff, Grade.EXCELLENT);
        s1.addGrade(new Subject("Химия", true), Grade.EXCELLENT);
        s1.addGrade(new Subject("Биология", true), Grade.GOOD);

        recordBook.addSemester(s1);
        recordBook.setDiplomWorkGrade(Grade.EXCELLENT);

        assertTrue(recordBook.canGetRedDiploma());
    }

    @Test
    void shouldNotGetRedDiplomaIfDiplomaWorkIsNotExcellent() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        recordBook.addSemester(s1);

        recordBook.setDiplomWorkGrade(Grade.GOOD); // ВКР на 4

        assertFalse(recordBook.canGetRedDiploma());
    }

    @Test
    void shouldPredictRedDiplomaBeforeDiplomaWork() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        recordBook.addSemester(s1);

        // ВКР еще не защищена (null), но прогноз положительный
        assertTrue(recordBook.canGetRedDiploma());
    }

    // ==================== ТЕСТЫ СТИПЕНДИИ ====================

    @Test
    void shouldNotGetScholarshipIfEmpty() {
        assertFalse(recordBook.canGetIncreasedScholarship());
    }

    @Test
    void shouldGetScholarshipIfOnlyGoodAndExcellent() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.EXCELLENT);
        s1.addGrade(physicsExam, Grade.GOOD);
        recordBook.addSemester(s1);

        assertTrue(recordBook.canGetIncreasedScholarship());
    }

    @Test
    void shouldNotGetScholarshipIfHasSatisfactoryInCurrentSemester() {
        Semester s1 = new Semester(1);
        s1.addGrade(mathExam, Grade.GOOD);

        Semester s2 = new Semester(2);
        s2.addGrade(physicsExam, Grade.ENOUGH); // Тройка в текущем семестре

        recordBook.addSemester(s1);
        recordBook.addSemester(s2);

        assertFalse(recordBook.canGetIncreasedScholarship());
    }

    @Test
    void shouldNotGetScholarshipIfHasDiffFailInCurrentSemester() {
        Semester s1 = new Semester(1);
        s1.addGrade(historyDiff, Grade.DIFF_FAIL); // Незачет
        recordBook.addSemester(s1);

        assertFalse(recordBook.canGetIncreasedScholarship());
    }
}