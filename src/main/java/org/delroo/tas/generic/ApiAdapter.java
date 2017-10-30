package org.delroo.tas.generic;

import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;

public class ApiAdapter<T extends UriResource> {

    protected final RequestSpecification requestSpec;
    private final T resourceSpec;

    public ApiAdapter(T resourceSpec) {
        this.resourceSpec = resourceSpec;
        this.requestSpec = given().baseUri(resourceSpec.getUri());
    }

    public T getResourceSpec() {
        return resourceSpec;
    }
}
