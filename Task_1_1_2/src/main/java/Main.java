package ru.example.blackjack;

/**
 * Точка входа в приложение Blackjack.
 */
public class Main {

    /**
     * Запускает игру и передаёт управление игровому объекту.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}