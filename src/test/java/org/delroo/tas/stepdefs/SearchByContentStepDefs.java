package org.delroo.tas.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.delroo.tas.domain.TheGuardianApiClient;
import org.delroo.tas.domain.model.SearchContentResponse;

import static junit.framework.TestCase.fail;
import static org.delroo.tas.util.ListUtils.isNullOrEmpty;
import static org.junit.Assert.assertTrue;

public class SearchByContentStepDefs {

    private TheGuardianApiClient client;
    private SearchContentResponse searchContentResponse;

    @Given("^John has access to TheGuardian API app$")
    public void userHasAccessToTheGuardianAPIApp() {
        client = new TheGuardianApiClient("test");
    }

    @When("^(?:he|user) searches items with '(.*)' content$")
    public void userSearchesItemsWithContent(String content) {
        searchContentResponse = client.searchContent(content);
    }

    @Then("^(?:he|user) receives all the items with '(.*)' content$")
    public void userReceivesAllTheItemsWithCarContent(final String content) {
        ensureResponseHasContentAvailable();
        searchContentResponse.getResponse().getResults().forEach(item ->
                assertTrue("Returned item does NOT relate to content: " + content, item.relatesTo(content))
        );
    }

    private void ensureResponseHasContentAvailable() {
        if (searchContentResponse == null ||
                searchContentResponse.getResponse() == null ||
                isNullOrEmpty(searchContentResponse.getResponse().getResults())) {
            fail("Cannot verify items content: null or empty response");
        }
    }
}
