package org.delroo.tas.util;

import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class BooleanEval extends AbstractEvaluator<String> {

    private final static Operator NOT =
            new Operator("NOT", 1, Operator.Associativity.RIGHT, 1);
    private final static Operator OR =
            new Operator("OR", 2, Operator.Associativity.LEFT, 2);
    private final static Operator AND =
            new Operator("AND", 2, Operator.Associativity.LEFT, 3);
    private final static Operator OR_NOT =
            new Operator("OR NOT", 2, Operator.Associativity.LEFT, 2);
    private final static Operator AND_NOT =
            new Operator("AND NOT", 2, Operator.Associativity.LEFT, 3);

    private static final Parameters PARAMETERS;

    static {
        PARAMETERS = new Parameters();

        PARAMETERS.add(NOT);
        PARAMETERS.add(OR);
        PARAMETERS.add(AND);
        PARAMETERS.add(AND_NOT);
        PARAMETERS.add(OR_NOT);

        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
    }

    private final Predicate<String> predicate;

    public BooleanEval(final Predicate<String> function) {
        super(PARAMETERS);
        this.predicate = function;
    }

    @Override
    protected String toValue(String literal, Object evaluationContext) {
        return String.valueOf(predicate.test(literal));
    }

    @Override
    protected String evaluate(Operator operator, Iterator<String> operands, Object evaluationContext) {
        List<String> tree = (List<String>) evaluationContext;
        String o1 = operands.next();
        Boolean result;
        if (operator == OR) {
            result = getValue(o1) || getValue(operands.next());
        } else if (operator == AND) {
            result = getValue(o1) && getValue(operands.next());
        } else if (operator == AND_NOT) {
            result = getValue(o1) && !getValue(operands.next());
        } else if (operator == OR_NOT) {
            result = getValue(o1) || !getValue(operands.next());
        } else if (operator == NOT) {
            result = !getValue(o1);
        } else {
            throw new IllegalArgumentException();
        }
        String eval = String.valueOf(result);
        tree.add(eval);
        return eval;
    }

    private boolean getValue(String literal) {
        if (literal.equalsIgnoreCase("true") ||
                literal.equalsIgnoreCase("false"))
            return Boolean.valueOf(literal);
        else
            return predicate.test(literal);
    }
}