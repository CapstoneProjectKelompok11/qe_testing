package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.ListingPage;
import starter.pages.LoginPage;

public class ListingSteps {

    @Steps
    ListingPage listingPage;

    @Steps
    LoginPage loginPage;

    @When("I click rent an office menu")
    public void iClickRentAnOfficeMenu() {
        loginPage.clickRentOfficeMenu();
    }

    @Then("I validate on listing page")
    public void iValidateOnListingPage() {
        listingPage.headerAppears();
        listingPage.headerTextEqual();
        listingPage.validateOnListingPage();
    }
}