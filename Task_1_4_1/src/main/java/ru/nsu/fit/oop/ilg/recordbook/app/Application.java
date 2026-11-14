package ru.nsu.fit.oop.ilg.recordbook.app;

import java.util.Scanner;

import ru.nsu.fit.oop.ilg.recordbook.core.Grade;
import ru.nsu.fit.oop.ilg.recordbook.core.RecordBook;
import ru.nsu.fit.oop.ilg.recordbook.core.Semester;
import ru.nsu.fit.oop.ilg.recordbook.core.Subject;

public class Application {
    private final Scanner scanner;
    private RecordBook recordBook;
    public Application() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Электронная зачетная книжка ФИТ");
        System.out.print("Вы на платной форме обучения? (1 - да, 0 - нет): ");
        boolean isPaid = scanner.nextLine().trim().equals("1");
        recordBook = new RecordBook(isPaid);
        boolean running = true;
        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить семестр");
            System.out.println("2 - Добавить оценку в семестр");
            System.out.println("3 - Установить оценку за ВКР");
            System.out.println("4 - Узнать средний балл");
            System.out.println("5 - Проверить перевод на бюджет");
            System.out.println("6 - Проверить красный диплом");
            System.out.println("7 - Проверить повышенную стипендию");
            System.out.println("0 - Выход");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addSemester();
                case "2" -> addGrade();
                case "3" -> setDiplomaWork();
                case "4" -> System.out.printf("Средний балл: %.2f%n", recordBook.getAverageGrade());
                case "5" -> System.out.println("Можно перевестись: " + (recordBook.canTransferToBudget() ? "Да" : "Нет"));
                case "6" -> System.out.println("Светит красный диплом: " + (recordBook.canGetRedDiploma() ? "Да" : "Нет"));
                case "7" -> System.out.println("Можно получить повышенную стипендию: " + (recordBook.canGetIncreasedScholarship() ? "Да" : "Нет"));
                case "0" -> running = false;
                default -> System.out.println("Неверный ввод.");
            }
        }
        scanner.close();
    }

    private void addSemester(){
        System.out.print("Введите номер семестра: ");
        int number =Integer.parseInt(scanner.nextLine().trim());
        recordBook.addSemester(new Semester(number));
        System.out.println("Семестр добавлен.");
    }
    private void addGrade(){
        System.out.print("Введите номер семестра: ");
        int number = Integer.parseInt(scanner.nextLine().trim());
        Semester current=recordBook.getSemester(number);
        if (current==null) {
            System.out.println("Семестр с таким номером не найден!");
            return;
        }
        System.out.print("Название предмета: ");
        String name= scanner.nextLine().trim();
        System.out.print("Это экзамен? (1 - да, 0 - нет): ");
        boolean isExam=scanner.nextLine().trim().equals("1");
        Subject subject=new Subject(name, isExam);
        System.out.println("Выберите оценку: EXCELLENT, GOOD, ENOUGH, BAD, DIFF_PASS, DIFF_FAIL");
        String gradeStr=scanner.nextLine().trim().toUpperCase();
        try{
            Grade grade=Grade.valueOf(gradeStr);
            current.addGrade(subject, grade);
            System.out.println("Оценка добавлена.");
        } catch (IllegalArgumentException e){
            System.out.println("Неверная оценка.");
        }
    }

    private void setDiplomaWork(){
        System.out.println("Введите оценку за ВКР (EXCELLENT, GOOD, ENOUGH, BAD):");
        String gradeStr = scanner.nextLine().trim().toUpperCase();
        try {
            Grade grade = Grade.valueOf(gradeStr);
            recordBook.setDiplomWorkGrade(grade);
            System.out.println("Оценка за ВКР установлена.");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная оценка.");
        }
    }
}