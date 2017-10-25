package org.delroo.tas.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class SampleTestStepDef {

    private int nrOfFruits;

    @Given("^I have (\\d+) apples?$")
    public void iHaveApple(int nrOfFruits) {
        this.nrOfFruits = nrOfFruits;
    }

    @When("^I give away (\\d+) apples?$")
    public void iGiveAwayApple(int gaveAwayFruits) {
        this.nrOfFruits -= gaveAwayFruits;
    }

    @Then("^I should have (\\d+) apples? left$")
    public void iShouldHaveApplesLeft(int fruitsLeft) {
        Assert.assertEquals(fruitsLeft, this.nrOfFruits);
    }
}