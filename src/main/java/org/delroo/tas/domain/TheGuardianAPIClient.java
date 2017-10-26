package org.delroo.tas.domain;

public class TheGuardianAPIClient {

    private final String apiKey;

    public TheGuardianAPIClient(final String apiKey) {
        this.apiKey = apiKey;
    }

    public SearchContentResponse searchContent(String content) {
        return new SearchContentResponse();
    }
}
