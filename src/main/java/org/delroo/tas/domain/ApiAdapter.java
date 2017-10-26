package org.delroo.tas.domain;

import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;

public class ApiAdapter {

    protected final RequestSpecification requestSpecification;

    public ApiAdapter() {
        this.requestSpecification = given().baseUri("https://content.guardianapis.com");
    }
}
