package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.auth.PostLogin;
import starter.city.GetCity;
import starter.city.PostCity;
import starter.review.GetReview;
import starter.review.PostReview;

import java.io.IOException;

public class ReviewSteps {

    @Steps
    PostLogin postLogin;

    @Steps
    GetReview getReview;

    @Steps
    PostReview postReview;

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

    @Given("I have logged in as user")
    public void iHaveLoggedInAsUser() throws Exception {
        postLogin.setPostEndpointLogin();
        postLogin.requestPostDetailLogin("string", "string");
        postLogin.validateStatusCode(200);
    }

    @And("I get token from the response")
    public void iGetTokenFromTheResponse() {
        postLogin.getToken();
    }

    @Given("I set an endpoint for POST review")
    public void iSetAnEndpointForPOSTReview() {
        postReview.setPostEndpointReview();
    }

//    @When("I request POST detail review with {string} and input {int}, {string}, and {int}")
//    public void iRequestPOSTDetailReviewWithAndInputBuildingIdAndRating(String status, int buildingId, String review, int rating) throws IOException {
//        postReview.requestPostDetailReview(status, buildingId, review, rating);
//    }

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
}
