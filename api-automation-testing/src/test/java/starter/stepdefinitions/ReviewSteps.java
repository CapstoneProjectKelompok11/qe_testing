package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.PostLogin;
import starter.review.GetAllReview;
import starter.review.GetReview;
import starter.review.PostReview;
import starter.review.PutApprovalReview;

import java.io.IOException;

public class ReviewSteps {

    @Steps
    PostLogin postLogin;

    @Steps
    GetAllReview getAllReview;

    @Steps
    GetReview getReview;

    @Steps
    PostReview postReview;

    @Steps
    PutApprovalReview putApprovalReview;

    @Given("I have logged in as user")
    public void iHaveLoggedInAsUser() throws Exception {
        postLogin.setPostEndpointLogin();
        postLogin.requestPostDetailLogin("user@gmail.com", "user");
        postLogin.validateStatusCode(200);
    }

    @And("I get token from the response")
    public void iGetTokenFromTheResponse() {
        postLogin.getToken();
    }

    @Given("I set an endpoint for GET all review")
    public void iSetAnEndpointForGETAllReview() {
        getAllReview.setGetEndpointAllReview();
    }

    @When("I request GET detail review with {string} and input {string}")
    public void iRequestGETDetailReviewWithAndInput(String status, String token) throws IOException {
        getAllReview.requestGetDetailReview(status, token);
    }

    @Then("I validate the status code for get all review is {int}")
    public void iValidateTheStatusCodeForGetAllReviewIsStatusCode(int statusCode) {
        getAllReview.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and all data details of review")
    public void validateTheAndAllDataDetailsOfReview(String message) {
        getAllReview.validateTheDataDetailsOfReview(message);
    }

    @Given("I set an endpoint for POST review")
    public void iSetAnEndpointForPOSTReview() {
        postReview.setPostEndpointReview();
    }

    @When("I request POST detail review with {string}, {string} and input {int}, {string}, and {int}")
    public void iRequestPOSTDetailReviewWithAndInputBuildingIdAndRating(String statusReview, String status, int buildingId, String review, int rating) throws IOException {
        postReview.requestPostDetailReview(statusReview, status, buildingId, review, rating);
    }

    @Then("I validate the status code for post review is {int}")
    public void iValidateTheStatusCodeForPostReviewIsStatusCode(int statusCode) {
        postReview.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post review")
    public void validateTheAndDataDetailsAfterPostReview(String message) {
        postReview.validateDataDetailAfterPostReview(message);
    }

    @Given("I set an endpoint for GET review")
    public void iSetAnEndpointForGETReview() {
        getReview.setGetEndpointReview();
    }

    @When("I request GET detail review with input {int}, {int}, and {int}")
    public void iRequestGETDetailReviewWithInputBuildingIdPageAndLimit(int buildingId, int page, int limit) {
        getReview.requestGetDetailReview(buildingId, page, limit);
    }

    @Then("I validate the status code for get review is {int}")
    public void iValidateTheStatusCodeForGetReviewIsStatusCode(int statusCode) {
        getReview.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of review")
    public void validateTheAndDataDetailsOfReview(String message) {
        getReview.validateTheDataDetailsOfReview(message);
    }

    @Given("I set an endpoint for PUT approval review")
    public void iSetAnEndpointForPUTApprovalReview() {
        putApprovalReview.setPUTEndpointApprovalReview();
    }

    @When("I request PUT detail approval review with {string} and input {string}, {int}, and {string}")
    public void iRequestPUTDetailApprovalReviewWithAndInput(String status, String userId, int buildingId, String approved) throws IOException {
        putApprovalReview.requestPUTApprovalReview(status, userId, buildingId, approved);
    }

    @Then("I validate the status code for put approval review is {int}")
    public void iValidateTheStatusCodeForPutApprovalReviewIsStatusCode(int statusCode) {
        putApprovalReview.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details after put approval review")
    public void validateTheAndDataDetailsAfterPutApprovalReview(String message) {
        putApprovalReview.validateTheDataDetailsOfReview(message);
    }
}
