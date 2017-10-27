package org.delroo.tas.util;

import java.util.ArrayList;
import java.util.function.Predicate;

public final class ContentValidator {

    private ContentValidator() {
    }

    public static boolean evaluateExpression(final Predicate<String> function, final String expression) {
        if (function == null || expression == null)
            throw new IllegalArgumentException("Cannot evaluate using null function or expression");
        return Boolean.valueOf(new BooleanEval(function).evaluate(expression, new ArrayList<>()));
    }
}
