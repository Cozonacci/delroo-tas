package org.delroo.tas.query;

import static org.delroo.tas.util.StringUtils.isNullOrEmpty;

public final class QueryFactory {

    public static Query createQuery(final String query) {
        if (isNullOrEmpty(query)) throw new IllegalArgumentException("Cannot create query from null or empty input.");
        if (isPhraseQuery(query)) return new PhraseQuery(query);
        if (isExpressionQuery(query)) return new ExpressionQuery(query);
        return new SimpleQuery(query);
    }

    private static boolean isPhraseQuery(String query) {
        return query.startsWith("\"") || query.endsWith("\"");
    }

    private static boolean isExpressionQuery(String query) {
        return query.contains("AND") || query.contains("OR") || query.contains("NOT")
                || (query.contains("(") && query.contains(")"));
    }
}
