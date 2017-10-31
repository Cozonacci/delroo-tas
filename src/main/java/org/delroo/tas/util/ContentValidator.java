package org.delroo.tas.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.delroo.tas.query.Query;
import org.delroo.tas.query.QueryFactory;
import org.delroo.tas.evaluator.BooleanEval;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.delroo.tas.util.StringUtils.*;

public final class ContentValidator {

    private final static Logger logger = LogManager.getLogger(ContentValidator.class);

    private ContentValidator() {
    }

    public static boolean evaluateExpression(final Predicate<String> function, final String expression) {
        if (function == null || expression == null)
            throw new IllegalArgumentException("Cannot evaluate using null function or expression");
        return Boolean.valueOf(new BooleanEval(function).evaluate(expression, new ArrayList<>()));
    }

    /**
     * NOTE: Results returning other forms of word (e.g. singular when plural requested)
     * are not considered as relevant for search context
     */
    public static boolean contentRelatesTo(String content, String query) {
        Query q = QueryFactory.createQuery(query);
        return contentRelatesTo(content, q);
    }

    public static boolean contentRelatesTo(String content, Query query) {
        switch (query.getType()) {
            case WORD:
                return ensureTextMatch(content, query);
            case PHRASE:
                return ensurePhraseMatch(content, query);
            case EXPRESSION:
                return ensureExpressionMatch(content, query);
            default:
                throw new RuntimeException("Could not verify content: unknown query type");
        }
    }

    public static boolean ensureTextMatch(String content, Query query) {
        boolean result = containsIgnoreCases(content, query.getValue());
        logger.info(String.format("%S: Content [%s] matches %s", result, content, query));
        return result;
    }

    public static boolean ensurePhraseMatch(String content, Query query) {
        final String simplifiedContent = getHtmlText(content);
        return ensureTextMatch(simplifiedContent, query);
    }

    public static boolean ensureExpressionMatch(String content, Query query) {
        final String simplifiedContent = getUniqueWords(getHtmlText(content)).toString();
        boolean result = evaluateExpression(Predicates.containsIgnoreCases(simplifiedContent), query.getValue());
        logger.info(String.format("%S: Content [%s] matches %s", result, content, query));
        return result;
    }
}
