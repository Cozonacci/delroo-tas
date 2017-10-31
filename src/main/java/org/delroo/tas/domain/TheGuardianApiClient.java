package org.delroo.tas.domain;

import com.jayway.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.delroo.tas.domain.model.SearchContentResponse;
import org.delroo.tas.generic.ApiAdapter;

import java.util.HashMap;
import java.util.Map;

public class TheGuardianApiClient extends ApiAdapter<TheGuardianConfig> {

    private final static Logger logger = LogManager.getLogger(TheGuardianApiClient.class);

    private Map<String, Object> requestParams = new HashMap<>();

    public TheGuardianApiClient() {
        super(TheGuardianConfig.fromConfig());
        traceConstructor();
    }

    public TheGuardianApiClient(final String apiKey) {
        super(TheGuardianConfig.fromConfig().withApiKey(apiKey));
        traceConstructor();
    }

    public SearchContentResponse searchContent(String content) {
        requestParams.put("q", content);
        requestParams.put("show-fields", "body");
        requestParams.put("api-key", getResourceSpec().getApiKey());

        return retrieveResult(getResourceSpec().getPaths().get("searchContent").toString());
    }

    public SearchContentResponse searchSections(String section) {
        requestParams.put("q", section);
        requestParams.put("api-key", getResourceSpec().getApiKey());

        return retrieveResult(getResourceSpec().getPaths().get("searchSections").toString());
    }

    private SearchContentResponse retrieveResult(final String path) {
        logger.debug("Requesting {} with params {}", path, requestParams.toString());
        return requestSpec
                .with().params(requestParams)
                .when().get(path)
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .and().extract().body().as(SearchContentResponse.class);
    }

    private void traceConstructor() {
        logger.debug("Initiating a new TheGuardian Api Client with [{}] api-key", getResourceSpec().getApiKey());
    }
}
