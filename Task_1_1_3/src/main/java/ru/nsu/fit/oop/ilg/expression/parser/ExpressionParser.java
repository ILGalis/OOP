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
    private int position;

    /**
     * Создаёт парсер.
     *
     * @param input строка с выражением
     */
    public ExpressionParser(String input) {
        this.input = input.replaceAll("\\s+", "");
        this.position = 0;
    }

    /**
     * Парсит выражение целиком.
     *
     * @return дерево выражения
     */
    public Expression parse() {
        return parseExpression();
    }

    /**
     * Парсит одно выражение.
     *
     * @return дерево выражения
     */
    private Expression parseExpression() {
        if (peek() == '(') {
            return parseBinaryOperation();
        }

        if (Character.isDigit(peek()) || peek() == '-') {
            return parseNumber();
        }

        return parseVariable();
    }

    /**
     * Парсит бинарную операцию в скобках.
     *
     * @return дерево операции
     */
    private Expression parseBinaryOperation() {
        next();

        String inner = readUntilMatchingBracket();
        int opIndex = findTopLevelOperator(inner);
        char operator = inner.charAt(opIndex);

        String leftStr = inner.substring(0, opIndex);
        String rightStr = inner.substring(opIndex + 1);

        Expression left = new ExpressionParser(leftStr).parse();
        Expression right = new ExpressionParser(rightStr).parse();

        return createOperation(operator, left, right);
    }

    /**
     * Читает содержимое скобок до парной закрывающей.
     *
     * @return строка внутри скобок
     */
    private String readUntilMatchingBracket() {
        int depth = 1;
        int start = position;
        while (position < input.length() && depth > 0) {
            char c = input.charAt(position);
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            }
            position++;
        }
        return input.substring(start, position - 1);
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
            } else if (depth == 0 && (c == '+' || c == '-' || c == '*'
                    || c == '/')) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Создаёт операцию по символу.
     *
     * @param operator символ
     * @param left     левый операнд
     * @param right    правый операнд
     * @return операция
     */
    private Expression createOperation(char operator, Expression left,
                                       Expression right) {
        if (operator == '+') {
            return new Add(left, right);
        }
        if (operator == '-') {
            return new Sub(left, right);
        }
        if (operator == '*') {
            return new Mul(left, right);
        }
        return new Div(left, right);
    }

    /**
     * Парсит число.
     *
     * @return число
     */
    private Expression parseNumber() {
        int start = position;
        if (peek() == '-') {
            position++;
        }
        while (position < input.length()
                && Character.isDigit(input.charAt(position))) {
            position++;
        }
        int value = Integer.parseInt(input.substring(start, position));
        return new Number(value);
    }

    /**
     * Парсит имя переменной.
     *
     * @return переменная
     */
    private Expression parseVariable() {
        int start = position;
        while (position < input.length()
                && Character.isLetterOrDigit(input.charAt(position))) {
            position++;
        }
        return new Variable(input.substring(start, position));
    }

    /**
     * Возвращает текущий символ.
     *
     * @return символ
     */
    private char peek() {
        return input.charAt(position);
    }

    /**
     * Переходит к следующему символу.
     */
    private void next() {
        position++;
    }
}