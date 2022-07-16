package starter.review;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class GetAllReview {

    private String token;

    @Step("I set an endpoint for GET all review")
    public String setGetEndpointAllReview() {
        return base_url + "/admin/review";
    }

    @Step("I request GET detail review")
    public void requestGetDetailReview(String status, String token) throws IOException {
        if (token.equals("valid")) {
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        } else if (token.equals("invalid")) {
            this.token = "asal";
        } else {
            this.token = token;
        }

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .when().get(setGetEndpointAllReview());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .when().get(setGetEndpointAllReview());
        }
    }

    @Step("I validate the status code is for get review is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of review")
    public void validateTheDataDetailsOfReview(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("success")) {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data[0].user.id", equalTo(getDataUserID()));
        } else {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/review"));
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
}
