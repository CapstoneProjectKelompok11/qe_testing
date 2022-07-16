package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.*;

import java.io.IOException;

public class AuthSteps {

    @Steps
    PutProfile putProfile;

    @Steps
    PostRegister postRegister;

    @Steps
    PostLogin postLogin;

    @Steps
    PostProfileImage postProfileImage;

    @Steps
    GetProfileImage getProfileImage;

    @Steps
    GetProfile getProfile;

    @Given("I set an endpoint for PUT profile")
    public void iSetAnEndpointForPUTProfile() {
        putProfile.setPUTEndpointProfile();
    }

    @When("I request PUT  detail profile with {string}, input {string}, {string}, {string}, and {string}")
    public void iRequestPUTDetailProfileWithInputAnd(String status, String fname, String lname, String phone, String email) throws Exception {
        putProfile.requestPUTDetailProfile(status, fname, lname, phone, email);
    }

    @Then("I validate the status code for put profile is {int}")
    public void iValidateTheStatusCodeForPutProfileIsStatusCode(int statusCode) {
        putProfile.validateStatusCode(statusCode);
    }

    @And("validate the data details and the {string} after edit profile")
    public void validateTheDataDetailsAndTheAfterEditProfile(String message) {
        putProfile.validateDataDetailAfterEditProfile(message);
    }

    @Given("I set an endpoint for POST register")
    public void iSetAnEndpointForPOSTRegister() {
        postRegister.setPostEndpointRegister();
    }

    @When("I request POST detail register with input {string}, {string}, {string}, {string} and {string}")
    public void iRequestPOSTDetailRegisterWithInputAnd(String fname, String lname, String phone, String email, String password) throws Exception {
        postRegister.requestPostDetailRegister(fname, lname, phone, email, password);
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIsStatusCode(int statusCode) {
        postLogin.validateStatusCode(statusCode);
        postRegister.validateStatusCode(statusCode);
    }

    @And("validate the data details and the {string} after register")
    public void validateTheDataDetailsAndTheAfterRegister(String message) {
        postRegister.validateDataDetailAfterRegister(message);
    }

    @Given("I set an endpoint for POST login")
    public void iSetAnEndpointForPOSTLogin() {
        postLogin.setPostEndpointLogin();
    }

    @When("I request POST detail login with input {string} and {string}")
    public void iRequestPOSTDetailLoginWithInputAnd(String email, String password) throws Exception {
        postLogin.requestPostDetailLogin(email, password);
    }

    @And("validate the data details and the {string} after login")
    public void validateTheDataDetailsAndTheAfterLogin(String message) {
        postLogin.validateDataDetailAfterLogin(message);
    }

    @Given("I set an endpoint for POST profile image")
    public void iSetAnEndpointForPOSTProfileImage() {
        postProfileImage.setPostEndpointProfileImage();
    }

    @When("I request POST detail profile image with {string} and input {string}")
    public void iRequestPOSTDetailProfileImageWithAndInput(String status, String image) throws IOException {
        postProfileImage.requestPostDetailProfileImage(status, image);
    }

    @Then("I validate the status code for post profile image is {int}")
    public void iValidateTheStatusCodeForPostProfileImageIsStatusCode(int statusCode) {
        postProfileImage.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post profile image")
    public void validateTheAndDataDetailsAfterPostProfileImage(String message) {
        postProfileImage.validateDataDetailAfterPostProfileImage(message);
    }

    @Given("I set an endpoint for GET profile image")
    public void iSetAnEndpointForGETProfileImage() {
        getProfileImage.setGetEndpointProfileImage();
    }

    @When("I request GET detail profile image with input {string}")
    public void iRequestGETDetailProfileImageWithInput(String filename) throws IOException {
        getProfileImage.requestGetDetailProfileImage(filename);
    }

    @Then("I validate the status code for get profile image is {int}")
    public void iValidateTheStatusCodeForGetProfileImageIsStatusCode(int statusCode) {
        getProfileImage.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data profile image")
    public void validateTheAndDataProfileImage(String message) {
        getProfileImage.validateTheDataDetailsOfProfileImage(message);
    }

    @Given("I set an endpoint for GET profile")
    public void iSetAnEndpointForGETProfile() {
        getProfile.setGetEndpointProfile();
    }

    @When("I request GET detail profile with {string} and {string}")
    public void iRequestGETDetailProfileWithAnd(String status, String token) throws IOException {
        getProfile.requestGetDetailProfile(status, token);
    }

    @Then("I validate the status code for get profile is {int}")
    public void iValidateTheStatusCodeForGetProfileIs(int statusCode) {
        getProfile.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of profile")
    public void validateTheAndDataDetailsOfProfile(String message) {
        getProfile.validateTheDataDetailsOfProfile(message);
    }
}
