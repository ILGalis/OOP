package ru.nsu.fit.oop.ilg.recordbook.core;

public class Subject{
    private final String name;
    private final boolean isExam;
    public Subject(String name,boolean isExam){
        this.name = name;
        this.isExam = isExam;
    }

    public String getSubjectName(){
        return name;
    }

    public boolean isExam(){
        return isExam;
    }
}