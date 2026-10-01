package ru.nsu.fit.oop.ilg.expression.operation;

import ru.nsu.fit.oop.ilg.expression.core.BinaryOperation;
import ru.nsu.fit.oop.ilg.expression.core.Expression;

/**
 * Деление.
 */
public class Div extends BinaryOperation {

    /**
     * Создаёт деление.
     *
     * @param left  левый операнд
     * @param right правый операнд
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "/";
    }

    @Override
    protected int calculate(int left, int right) {
        if (right == 0) {
            return 0;
        }
        return left / right;
    }

    @Override
    public Expression derivative(String variable) {
        Expression u = left;
        Expression v = right;
        Expression du = left.derivative(variable);
        Expression dv = right.derivative(variable);
        return new Div(
                new Sub(new Mul(du, v), new Mul(u, dv)),
                new Mul(v, v)
        );
    }
}