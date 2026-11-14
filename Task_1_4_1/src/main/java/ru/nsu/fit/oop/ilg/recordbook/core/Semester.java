package ru.nsu.fit.oop.ilg.recordbook.core;

import java.util.HashMap;
import java.util.Map;

public class Semester{
    private final int semesterNumber;
    private final Map<Subject,Grade> grades;

    public Semester(int semesterNumber){
        this.semesterNumber = semesterNumber;
        this.grades = new HashMap<>();
    }

    public void addGrade(Subject subject,Grade grade){
        grades.put(subject,grade);
    }

    public int getSemesterNumber(){
        return semesterNumber;
    }

    public Map<Subject,Grade> getGrades(){
        return grades;
    }
}