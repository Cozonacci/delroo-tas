package org.delroo.tas.util;

import org.junit.Test;

import static org.delroo.tas.util.ContentValidator.containsIgnoreCases;
import static org.delroo.tas.util.ContentValidator.evaluateExpression;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContentValidatorTest {

    @Test
    public void shouldEvaluateComplexExpression() {
        assertTrue(evaluateExpression("apple pie"::contains, "apple AND pie AND NOT (cherry OR ice)"));
    }

    @Test
    public void shouldEvaluateIgnoringCase() {
        String content = "Apple pie";
        String query = "apple AND pie AND NOT (cherry OR ice)";

        assertFalse(evaluateExpression(content::contains, query));
        assertTrue(evaluateExpression(containsIgnoreCases(content), query));
    }
}
