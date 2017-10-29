package org.delroo.tas.query;

import static org.delroo.tas.util.StringUtils.innerValue;

public class PhraseQuery extends AbstractQuery {

    public PhraseQuery(String query) {
        super(query, QueryType.PHRASE);
    }

    @Override
    public String getValue() {
        return innerValue(getQuery());
    }
}
