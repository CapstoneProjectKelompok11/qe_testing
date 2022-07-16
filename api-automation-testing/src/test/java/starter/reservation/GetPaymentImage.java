package starter.reservation;

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

public class GetPaymentImage {

    private String filename, token;

    @Step("I set an endpoint for GET payment image")
    public String setGetEndpoint() {
        return base_url + "/admin/reservation/image/{filename}";
    }

    @Step("I request GET detail payment image")
    public void requestGetDetail(String status, String filename) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        if (filename.equals("valid")) {
            this.filename = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//paymentImage.json"), StandardCharsets.UTF_8);
        } else if (filename.equals("invalid")) {
            this.filename = "abc";
        } else {
            this.filename = "";
        }

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("filename", this.filename)
                    .when().get(setGetEndpoint());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("filename", this.filename)
                    .when().get(setGetEndpoint());
        }
    }

    @Step("I validate the status code is for get payment image is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of payment image")
    public void validateTheDataDetail(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body(notNullValue());
        } else {
            SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
            SerenityRest.then().body("status", equalTo(getStatus()));
            SerenityRest.then().body("error", equalTo(getError()));
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

    @Step("get status from the response")
    public int getStatus() {
        Response response = SerenityRest.lastResponse();
        int code = response.body().path("status");
        System.out.println("Code: " + code);
        return code;
    }

    @Step("get error from the response")
    public String getError() {
        Response response = SerenityRest.lastResponse();
        String message = response.body().path("error");
        System.out.println("Error: " + message);
        return message;
    }

    @Step("get path from the response")
    public String getPath() {
        Response response = SerenityRest.lastResponse();
        String path = response.body().path("path");
        System.out.println("Path: " + path);
        return path;
    }
}
