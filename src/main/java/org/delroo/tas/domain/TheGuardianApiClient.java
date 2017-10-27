package org.delroo.tas.domain;

import com.jayway.restassured.http.ContentType;
import org.delroo.tas.domain.model.SearchContentResponse;

import java.util.HashMap;
import java.util.Map;

public class TheGuardianApiClient extends ApiAdapter {

    private final String apiKey;
    private Map<String, Object> requestParams = new HashMap<>();

    public TheGuardianApiClient(final String apiKey) {
        this.apiKey = apiKey;
        requestParams.put("api-key", apiKey);
    }

    public SearchContentResponse searchContent(String content) {
        requestParams.put("q", content);
        requestParams.put("show-fields", "body");

        return requestSpecification

                .log().all()
                .with().params(requestParams)

                .when().get("/search")

                .then().log().all()
                .and().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .and().extract().body().as(SearchContentResponse.class);
    }
}
