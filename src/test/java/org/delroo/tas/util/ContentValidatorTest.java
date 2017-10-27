package org.delroo.tas.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ContentValidatorTest {

    @Test
    public void shouldEvaluateComplexExpression() {
        assertTrue(ContentValidator
                .evaluateExpression("apple pie"::contains, "apple AND pie AND NOT (cherry OR ice)"));
    }
}
