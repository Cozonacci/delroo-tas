package org.delroo.tas.domain;

import com.jayway.restassured.http.ContentType;
import org.delroo.tas.domain.model.SearchContentResponse;
import org.delroo.tas.generic.ApiAdapter;

import java.util.HashMap;
import java.util.Map;

public class TheGuardianApiClient extends ApiAdapter<TheGuardianConfig> {

    private Map<String, Object> requestParams = new HashMap<>();

    public TheGuardianApiClient() {
        super(TheGuardianConfig.fromConfig());
    }

    public TheGuardianApiClient(final String apiKey) {
        super(TheGuardianConfig.fromConfig().withApiKey(apiKey));
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
        return requestSpec

                .log().all()
                .with().params(requestParams)

                .when().get(path)

                .then().log().all()
                .and().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .and().extract().body().as(SearchContentResponse.class);
    }
}
