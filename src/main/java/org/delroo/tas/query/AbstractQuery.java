package org.delroo.tas.query;

public abstract class AbstractQuery implements Query {

    private final String query;
    private final QueryType type;

    public AbstractQuery(String query, QueryType type) {
        this.query = query;
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return String.format("[%s] query = [%s]", getType(), getValue());
    }

    @Override
    public QueryType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return query;
    }
}
