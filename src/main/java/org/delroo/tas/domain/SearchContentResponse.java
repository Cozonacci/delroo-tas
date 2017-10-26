package org.delroo.tas.domain;

import java.util.Collections;
import java.util.List;

public class SearchContentResponse {

    public List<Item> getResults() {
        return Collections.singletonList(new Item());
    }
}
