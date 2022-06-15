package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.PostLogin;
import starter.auth.PostRegister;

public class AuthSteps {

    @Steps
    PostLogin postLogin;

    @Steps
    PostRegister postRegister;

    @Given("I set an endpoint for POST login")
    public void iSetAnEndpointForPOSTLogin() {
        postLogin.setPostEndpointLogin();
    }

    @When("I request POST detail login with input {string} and {string}")
    public void iRequestPOSTDetailLoginWithInputAnd(String email, String password) throws Exception {
        postLogin.requestPostDetailLogin(email, password);
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIsStatusCode(int statusCode) {
        postLogin.validateStatusCode(statusCode);
        postRegister.validateStatusCode(statusCode);
    }

    @And("validate the data details and the {string} after login")
    public void validateTheDataDetailsAndTheAfterLogin(String message) {
        postLogin.validateDataDetailAfterLogin(message);
    }

    @Given("I set an endpoint for POST register")
    public void iSetAnEndpointForPOSTRegister() {
        postRegister.setPostEndpointRegister();
    }

    @When("I request POST detail register with input {string}, {string}, {string}, {string} and {string}")
    public void iRequestPOSTDetailRegisterWithInputAnd(String fname, String lname, String phone, String email, String password) throws Exception {
        postRegister.requestPostDetailRegister(fname, lname, phone, email, password);
    }

    @And("validate the data details and the {string} after register")
    public void validateTheDataDetailsAndTheAfterRegister(String message) {
        postRegister.validateDataDetailAfterRegister(message);
    }
}
