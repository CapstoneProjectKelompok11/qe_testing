package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.complex.GetComplex;
import starter.complex.PostComplex;

import java.io.IOException;

public class ComplexSteps {

    @Steps
    GetComplex getComplex;

    @Steps
    PostComplex postComplex;

    @Given("I set an endpoint for GET all complex with input {string}")
    public void iSetAnEndpointForGETAllComplexWithInput(String city) {
        getComplex.setGetEndpointComplex(city);
    }

    @When("I request GET detail complex with input {string}")
    public void iRequestGETDetailComplexWithInput(String city) {
        getComplex.requestGetDetailComplex(city);
    }

    @Then("I validate the status code for get all complex is {int}")
    public void iValidateTheStatusCodeForGetAllComplexIs(int statusCode) {
        getComplex.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of complex")
    public void validateTheAndDataDetailsOfComplex(String message) {
        getComplex.validateTheDataDetailsOfComplex(message);
    }

    @Given("I set an endpoint for POST complex")
    public void iSetAnEndpointForPOSTComplex() {
        postComplex.setPostEndpointComplex();
    }

    @When("I request POST detail complex with {string} and input {string} and {int}")
    public void iRequestPOSTDetailComplexWithAndInput(String status, String complex, int cityId) throws IOException {
        postComplex.requestPostDetailComplex(status, complex, cityId);
    }

    @Then("I validate the status code for post complex is {int}")
    public void iValidateTheStatusCodeForPostComplexIsStatusCode(int statusCode) {
        postComplex.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post complex")
    public void validateTheAndDataDetailsAfterPostComplex(String message) {
        postComplex.validateDataDetailAfterPostComplex(message);
    }
}
