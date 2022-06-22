package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.PostLogin;
import starter.city.GetCity;
import starter.city.PostCity;

import java.io.IOException;

public class CitySteps {

    @Steps
    PostLogin postLogin;

    @Steps
    GetCity getCity;

    @Steps
    PostCity postCity;

    @Given("I have logged in as admin")
    public void iHaveLoggedInAsAdmin() throws Exception {
        postLogin.setPostEndpointLogin();
        postLogin.requestPostDetailLogin("testingqe3@gmail.com", "testing123*");
        postLogin.validateStatusCode(200);
    }

    @And("I get token admin from the response")
    public void iGetTokenAdminFromTheResponse() {
        postLogin.getAdminToken();
    }

    @Given("I set an endpoint for GET city")
    public void iSetAnEndpointForGETCity() {
        getCity.setGetEndpointCity();
    }

    @When("I request GET detail city")
    public void iRequestGETDetailCity() {
        getCity.requestGetDetailCity();
    }

    @Then("I validate the status code for get all city is {int}")
    public void iValidateTheStatusCodeForGetAllCityIs(int statusCode) {
        getCity.validateTheStatusCode(statusCode);
    }

    @And("validate the data details of city")
    public void validateTheDataDetailsOfCity() {
        getCity.validateTheDataDetailsOfCity();
    }

    @Given("I set an endpoint for POST city")
    public void iSetAnEndpointForPOSTCity() {
        postCity.setPostEndpointCity();
    }

    @When("I request POST detail city with {string} and input {string}")
    public void iRequestPOSTDetailCityWithAndInput(String status, String city) throws IOException {
        postCity.requestPostDetailCity(status, city);
    }

    @Then("I validate the status code for post city is {int}")
    public void iValidateTheStatusCodeForPostCityIs(int statusCode) {
        postCity.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post city")
    public void validateTheAndDataDetailsAfterPostCity(String message) {
        postCity.validateDataDetailAfterPostCity(message);
    }
}
