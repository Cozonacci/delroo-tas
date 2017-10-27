package org.delroo.tas.evaluator;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BooleanEvalTest {

    private static String content = "sausages saveloy";
    private static BooleanEval evaluator = new BooleanEval(content::contains);

    private static boolean evaluate(String expression) {
        boolean result = Boolean.valueOf(evaluator.evaluate(expression, new ArrayList<>()));
        System.out.println(String.format("Evaluate expression: [%s] contains [%s] = %s", content, expression, result));
        return result;
    }

    @Test
    public void shouldEvaluateSingleTerm() {
        assertTrue(evaluate("sausages"));
    }

    @Test
    public void shouldEvaluateNegatedSingleTerm() {
        assertTrue(evaluate("NOT battered"));
        assertFalse(evaluate("NOT sausages"));
    }

    @Test
    public void shouldEvaluateAndOperator() {
        assertTrue(evaluate("sausages AND saveloy"));
        assertFalse(evaluate("sausages AND chips"));
        assertFalse(evaluate("chips AND sausages"));
    }

    @Test
    public void shouldEvaluateOrOperator() {
        assertTrue(evaluate("sausages OR saveloy"));
        assertTrue(evaluate("battered OR sausages"));
        assertFalse(evaluate("chips OR battered"));
    }

    @Test
    public void shouldEvaluateOrAndNotOperatorCombination() {
        assertFalse(evaluate("sausages AND NOT saveloy"));
        assertFalse(evaluate("battered OR NOT sausages"));
        assertFalse(evaluate("chips AND NOT apples"));
        assertTrue(evaluate("chips OR NOT apples"));
    }

    @Test
    public void shouldEvaluateParentheses() {
        assertTrue(evaluate("sausages AND (saveloy OR battered)"));
        assertFalse(evaluate("sausages AND NOT (saveloy OR battered)"));
        assertFalse(evaluate("chips AND (saveloy OR battered)"));

        assertTrue(evaluate("chips OR NOT (ham OR battered)"));
        assertFalse(evaluate("chips OR NOT (saveloy OR battered)"));
        assertTrue(evaluate("chips OR (NOT battered AND saveloy)"));
    }
}
