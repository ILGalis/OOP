package ru.nsu.fit.oop.ilg.recordbook.core;

public enum Grade{
    EXCELLENT(5),
    GOOD(4),
    ENOUGH(3),
    BAD(2),
    DIFF_FAIL(0),
    DIFF_PASS(0);

    private final int value;

    Grade(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public boolean isCountsOnAverage(){
        return this != DIFF_FAIL && this != DIFF_PASS;
    }

}