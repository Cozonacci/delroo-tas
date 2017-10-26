package org.delroo.tas.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.delroo.tas.domain.SearchContentResponse;
import org.delroo.tas.domain.TheGuardianAPIClient;

import static org.junit.Assert.assertTrue;

public class SearchByContentStepDefs {

    private TheGuardianAPIClient client;
    private SearchContentResponse searchContentResponse;

    @Given("^John has access to TheGuardian API app$")
    public void userHasAccessToTheGuardianAPIApp() {
        client = new TheGuardianAPIClient("test");
    }

    @When("^(?:he|user) searches items with '(.*)' content$")
    public void userSearchesItemsWithContent(String content) {
        searchContentResponse = client.searchContent(content);
    }

    @Then("^(?:he|user) receives all the items with '(.*)' content$")
    public void userReceivesAllTheItemsWithCarContent(final String content) {
        if (searchContentResponse == null ||
                searchContentResponse.getResults() == null || searchContentResponse.getResults().isEmpty()) {
            throw new RuntimeException("Cannot verify items content: null or empty response");
        }

        searchContentResponse.getResults().forEach(item ->
                assertTrue("Returned items do NOT relate to content: " + content, item.relatesTo(content))
        );
    }
}
