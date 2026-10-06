package ru.nsu.fit.oop.ilg.expression.parser;

import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Number;
import ru.nsu.fit.oop.ilg.expression.core.Variable;
import ru.nsu.fit.oop.ilg.expression.operation.Add;
import ru.nsu.fit.oop.ilg.expression.operation.Div;
import ru.nsu.fit.oop.ilg.expression.operation.Mul;
import ru.nsu.fit.oop.ilg.expression.operation.Sub;

/**
 * Парсер математических выражений.
 * Выражения записываются в скобках, например: (3+(2*x)).
 */
public class ExpressionParser {
    private final String input;

    /**
     * Создаёт парсер.
     *
     * @param input строка с выражением
     */
    public ExpressionParser(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    /**
     * Парсит выражение целиком.
     *
     * @return дерево выражения
     */
    public Expression parse() {
        return parseExpression(input);
    }

    /**
     * Парсит выражение.
     *
     * @param s строка выражения
     * @return дерево выражения
     */
    private Expression parseExpression(String s) {
        if (s.startsWith("(") && s.endsWith(")")) {
            return parseOperation(s.substring(1, s.length() - 1));
        }

        if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-') {
            return new Number(Integer.parseInt(s));
        }

        return new Variable(s);
    }

    /**
     * Парсит бинарную операцию.
     *
     * @param inner строка внутри скобок
     * @return дерево операции
     */
    private Expression parseOperation(String inner) {
        int opIndex = findTopLevelOperator(inner);
        char op = inner.charAt(opIndex);
        String leftStr = inner.substring(0, opIndex);
        String rightStr = inner.substring(opIndex + 1);

        Expression left = parseExpression(leftStr);
        Expression right = parseExpression(rightStr);

        return switch (op) {
            case '+' -> new Add(left, right);
            case '-' -> new Sub(left, right);
            case '*' -> new Mul(left, right);
            default -> new Div(left, right);
        };
    }

    /**
     * Находит индекс оператора верхнего уровня.
     *
     * @param s строка внутри скобок
     * @return индекс оператора
     */
    private int findTopLevelOperator(String s) {
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            } else if (depth == 0 && isOperator(c)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Проверяет, является ли символ оператором.
     *
     * @param c символ
     * @return {@code true}, если оператор
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}