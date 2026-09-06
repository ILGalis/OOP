package ru.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("5 + 3 = " + add(5, 3));
        System.out.println("10 - 4 = " + subtract(10, 4));
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }
}