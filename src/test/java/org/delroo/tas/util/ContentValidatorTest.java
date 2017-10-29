package org.delroo.tas.util;

import org.delroo.tas.query.ExpressionQuery;
import org.delroo.tas.query.PhraseQuery;
import org.delroo.tas.query.SimpleQuery;
import org.junit.Test;

import static org.delroo.tas.util.ContentValidator.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContentValidatorTest {

    private static final String TEXT_CONTENT = "I love apple cherry Pies";
    private static final String HTML_TEXT_CONTENT = "<p>I love apple <strong>cherry</strong> Pies</p>";

    @Test
    public void shouldEvaluateComplexExpression() {
        assertTrue(evaluateExpression(TEXT_CONTENT::contains, "apple AND love AND NOT (banana OR ice)"));
    }

    @Test
    public void shouldEvaluateContentMatchesWord() {
        assertTrue(ensureTextMatch(TEXT_CONTENT, new SimpleQuery("cherry")));
        assertFalse(ensureTextMatch(TEXT_CONTENT, new SimpleQuery("cherries")));
    }

    @Test
    public void shouldEvaluateContentMatchesPhrase() {
        assertTrue(ensurePhraseMatch(HTML_TEXT_CONTENT, new PhraseQuery("\"cherry pies\"")));
        assertFalse(ensurePhraseMatch(HTML_TEXT_CONTENT, new PhraseQuery("\"cherries pies\"")));
    }

    @Test
    public void shouldEvaluateContentMatchesExpression() {
        assertTrue(ensureExpressionMatch(HTML_TEXT_CONTENT, new ExpressionQuery("cherry AND pies")));
        assertFalse(ensureExpressionMatch(HTML_TEXT_CONTENT, new ExpressionQuery("cherries AND pies")));
    }

    @Test
    public void shouldDetermineQueryAndEvaluateContentMatch() {
        assertTrue(contentRelatesTo(TEXT_CONTENT, "cherry"));
        assertFalse(contentRelatesTo(TEXT_CONTENT, "cherries"));

        assertTrue(contentRelatesTo(HTML_TEXT_CONTENT, "\"cherry pies\""));
        assertFalse(contentRelatesTo(HTML_TEXT_CONTENT, "\"cherries pies\""));

        assertTrue(contentRelatesTo(HTML_TEXT_CONTENT, "cherry AND pies"));
        assertFalse(contentRelatesTo(HTML_TEXT_CONTENT, "cherries AND pies"));
    }
}
