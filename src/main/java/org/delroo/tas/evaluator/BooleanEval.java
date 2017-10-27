package org.delroo.tas.evaluator;

import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class BooleanEval extends AbstractEvaluator<String> {

    private final static Operator NOT =
            new BooleanOperator("NOT", 1, Operator.Associativity.RIGHT, 1, (a, b) -> !a);
    private final static Operator OR =
            new BooleanOperator("OR", 2, Operator.Associativity.LEFT, 2, (a, b) -> a || b);
    private final static Operator AND =
            new BooleanOperator("AND", 2, Operator.Associativity.LEFT, 3, (a, b) -> a && b);
    private final static Operator OR_NOT =
            new BooleanOperator("OR NOT", 2, Operator.Associativity.LEFT, 2, (a, b) -> a || !b);
    private final static Operator AND_NOT =
            new BooleanOperator("AND NOT", 2, Operator.Associativity.LEFT, 3, (a, b) -> a && !b);

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
        BooleanOperator booleanOperator = ((BooleanOperator) operator);
        Boolean result = booleanOperator.isSingleOperand() ? booleanOperator.getAppliance().test(getValue(operands.next()), null) :
                booleanOperator.getAppliance().test(getValue(operands.next()), getValue(operands.next()));
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