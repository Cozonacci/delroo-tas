package org.delroo.tas.evaluator;

import com.fathzer.soft.javaluator.Operator;

import java.util.function.BiPredicate;

public class BooleanOperator extends Operator {

    private final BiPredicate<Boolean, Boolean> appliance;
    private boolean isSingleOperand;

    public BooleanOperator(String symbol,
                           int operandCount,
                           Associativity associativity,
                           int precedence,
                           BiPredicate<Boolean, Boolean> appliance) {
        super(symbol, operandCount, associativity, precedence);
        this.appliance = appliance;
        this.isSingleOperand = operandCount == 1;
    }

    public boolean isSingleOperand() {
        return isSingleOperand;
    }

    public BiPredicate<Boolean, Boolean> getAppliance() {
        return appliance;
    }
}
