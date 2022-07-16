package starter.auth;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.General.base_url;

public class GetProfile {

    private String token;

    @Step("I set an endpoint for GET profile")
    public String setGetEndpointProfile() {
        return base_url + "/auth/profile";
    }

    @Step("I request GET detail profile")
    public void requestGetDetailProfile(String status, String token) throws IOException {
        if (token.equals("valid")) {
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        } else if (token.equals("invalid")){
            this.token = "abc";
        } else {
            this.token = "";
        }

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .when().get(setGetEndpointProfile());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .when().get(setGetEndpointProfile());
        }
    }

    @Step("I validate the status code is for get profile is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of profile")
    public void validateTheDataDetailsOfProfile(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("success")) {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.id", equalTo(getUserId()));
            SerenityRest.then().body("data.email", equalTo(getUserEmail()));
        } else {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo(getPath()));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get user id from the response")
    public int getUserId() {
        Response response = SerenityRest.lastResponse();
        int userId = response.body().path("data.id");
        System.out.println("ID: " + userId);
        return userId;
    }

    @Step("get user email from the response")
    public String getUserEmail() {
        Response response = SerenityRest.lastResponse();
        String userEmail = response.body().path("data.email");
        System.out.println("ID: " + userEmail);
        return userEmail;
    }

    @Step("get path from the response")
    public String getPath() {
        Response response = SerenityRest.lastResponse();
        String path = response.body().path("path");
        System.out.println("Path: " + path);
        return path;
    }
}
