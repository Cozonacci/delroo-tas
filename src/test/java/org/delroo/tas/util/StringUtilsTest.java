package org.delroo.tas.util;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.delroo.tas.util.StringUtils.getHtmlText;
import static org.delroo.tas.util.StringUtils.getUniqueWords;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void shouldReturnUniqueWordsInString() {
        String input = "apple car apple car";
        MatcherAssert.assertThat(getUniqueWords(input).toString(), is("[apple, car]"));
    }

    @Test
    public void shouldReturnUniqueWordsInHtmlString() {
        String input = "<p><strong><em>Serves 4</em><br/>650ml chicken or vegetable stock<br/>125g polenta<br/>" +
                "50g finely grated parmesan or other hard, strong cheese<br/>3tbsp finely chopped chives<br/>" +
                "50g polenta, to coat<br/>Extra virgin olive oil, to fry</strong></p>";

        MatcherAssert.assertThat(getUniqueWords(getHtmlText(input)).toString(),
                is("[chicken, other, strong, Serves, grated, vegetable, finely, cheese, oil, virgin, olive, " +
                        "chopped, chives, hard, stock, 650ml, or, 125g, polenta, 50g, fry, coat, 4, Extra, 3tbsp, " +
                        "to, parmesan]"));
    }

    @Test
    public void shouldRemoveFirstAndLastChars() {
        MatcherAssert.assertThat(StringUtils.innerValue("abc"), is("b"));
        MatcherAssert.assertThat(StringUtils.innerValue("a"), is("a"));
    }

    @Test
    public void shouldEvaluateIgnoringCase() {
        String content = "Apple CheRry pie";
        String query = "cherry pie";

        assertTrue(StringUtils.containsIgnoreCases(content, query));
    }
}
