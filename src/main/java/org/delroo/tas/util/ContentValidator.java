package org.delroo.tas.util;

import org.delroo.tas.evaluator.BooleanEval;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public final class ContentValidator {

    private ContentValidator() {
    }

    public static Predicate<String> containsIgnoreCases(String content){
        return (input) -> Pattern.compile(Pattern.quote(input), Pattern.CASE_INSENSITIVE).matcher(content).find();
    }

    public static boolean evaluateExpression(final Predicate<String> function, final String expression) {
        if (function == null || expression == null)
            throw new IllegalArgumentException("Cannot evaluate using null function or expression");
        return Boolean.valueOf(new BooleanEval(function).evaluate(expression, new ArrayList<>()));
    }
}
