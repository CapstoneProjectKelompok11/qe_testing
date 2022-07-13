package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.complex.GetAllComplex;
import starter.complex.GetComplexByName;
import starter.complex.PostComplex;

import java.io.IOException;

public class ComplexSteps {

    @Steps
    GetAllComplex getAllComplex;

    @Steps
    GetComplexByName getComplexByName;

    @Steps
    PostComplex postComplex;

    @Given("I set an endpoint for GET complex with input city")
    public void iSetAnEndpointForGETComplexWithInputCity() {
        getComplexByName.setGetEndpointComplex();
    }

    @When("I request GET detail complex with input {string}")
    public void iRequestGETDetailComplexWithInput(String city) {
        getComplexByName.requestGetDetailComplex(city);
    }

    @Then("I validate the status code for get complex is {int}")
    public void iValidateTheStatusCodeForGetComplexIs(int statusCode) {
        getComplexByName.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of complex")
    public void validateTheAndDataDetailsOfComplex(String message) {
        getComplexByName.validateTheDataDetailsOfComplex(message);
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

    @Given("I set an endpoint for GET all complex")
    public void iSetAnEndpointForGETAllComplex() {
        getAllComplex.setGetEndpointComplex();
    }

    @When("I request GET detail complex")
    public void iRequestGETDetailComplex() {
        getAllComplex.requestGetDetailComplex();
    }

    @Then("I validate the status code for get all complex is {int}")
    public void iValidateTheStatusCodeForGetAllComplexIs(int statusCode) {
        getAllComplex.validateTheStatusCode(statusCode);
    }

    @And("validate the data details of complex")
    public void validateTheDataDetailsOfComplex() {
        getAllComplex.validateTheDataDetailsOfComplex();
    }
}
