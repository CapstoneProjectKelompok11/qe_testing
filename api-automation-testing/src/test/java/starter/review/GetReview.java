package starter.review;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class GetReview {

    private int buildingId, page, limit;

    @Step("I set an endpoint for GET review")
    public String setGetEndpointReview() {
        return base_url + "/review?buildingId={buildingId}&page={page}&limit={limit}";
    }

    @Step("I request GET detail review")
    public void requestGetDetailReview(int buildingId, int page, int limit) {
        this.buildingId = buildingId;
        this.page = page;
        this.limit = limit;

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("buildingId", this.buildingId)
                .pathParam("page", this.page)
                .pathParam("limit", this.limit)
                .when().get(setGetEndpointReview());
    }

    @Step("I validate the status code is for get review is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of review")
    public void validateTheDataDetailsOfReview(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("error")) {
            SerenityRest.then().body("status.code", equalTo("UNKNOWN_ERROR"));
            SerenityRest.then().body("status.message", equalTo("Happened unknown error!"));
            SerenityRest.then().body("data", equalTo(null));
        } else if (message.equals("not found")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data[0].user.id", equalTo(getDataUserID()));
            SerenityRest.then().body("data[0].user.email", equalTo(getDataUserEmail()));
            SerenityRest.then().body("data[0].review_date", equalTo(getDataReviewDate()));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get data user id from the response")
    public int getDataUserID() {
        Response response = SerenityRest.lastResponse();
        int userId = response.body().path("data[0].user.id");
        System.out.println("ID: " + userId);
        return userId;
    }

    @Step("get data user email from the response")
    public String getDataUserEmail() {
        Response response = SerenityRest.lastResponse();
        String userEmail = response.body().path("data[0].user.email");
        System.out.println("City name: " + userEmail);
        return userEmail;
    }

    @Step("get data review date from the response")
    public String getDataReviewDate() {
        Response response = SerenityRest.lastResponse();
        String reviewDate = response.body().path("data[0].review_date");
        System.out.println("Review date: " + reviewDate);
        return reviewDate;
    }
}
