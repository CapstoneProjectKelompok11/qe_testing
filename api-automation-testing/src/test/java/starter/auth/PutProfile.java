package starter.auth;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import utils.General;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PutProfile {

    private String token, firstname, lastname, phone, email;

    @Step("I set an endpoint for PUT profile")
    public String setPUTEndpointProfile(){
        return base_url + "/auth/profile/edit";
    }

    @Step("I request PUT detail profile")
    public void requestPUTDetailProfile(String status, String firstname, String lastname, String phone, String email) throws Exception {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        if (email.equals("new")) {
            this.email = "userX@gmail.com";
        } else if (email.equals("existed")) {
            this.email = "user@gmail.com";
        } else {
            this.email = "";
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;

        JSONObject requestData = new JSONObject();
        requestData.put("first_name", this.firstname);
        requestData.put("last_name", this.lastname);
        requestData.put("phone", this.phone);
        requestData.put("email", this.email);

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .body(requestData.toJSONString())
                    .when().put(setPUTEndpointProfile());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .body(requestData.toJSONString())
                    .when().put(setPUTEndpointProfile());
        }
    }

    @Step("I validate the status code for edit profile is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after edit profile")
    public void validateDataDetailAfterEditProfile(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/profile/edit"));
        } else if (message.equals("success")) {
            SerenityRest.then().body("status.code", equalTo(getStatusCode()));
            SerenityRest.then().body("status.message", equalTo(getStatusMessage()));
            SerenityRest.then().body("data.id", equalTo(getUserId()));
            SerenityRest.then().body("data.email", equalTo(getUserEmail()));
        } else {
            SerenityRest.then().body("status.code", equalTo(getStatusCode()));
            SerenityRest.then().body("status.message", equalTo(getStatusMessage()));
            SerenityRest.then().body("data", equalTo(null));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get status code from the response")
    public String getStatusCode() {
        Response response = SerenityRest.lastResponse();
        String statusCode = response.body().path("status.code");
        System.out.println("Status code: " + statusCode);
        return statusCode;
    }

    @Step("get status message from the response")
    public String getStatusMessage() {
        Response response = SerenityRest.lastResponse();
        String statusMessage = response.body().path("status.message");
        System.out.println("Status message: " + statusMessage);
        return statusMessage;
    }

    @Step("get user id from the response")
    public int getUserId() {
        Response response = SerenityRest.lastResponse();
        int userId = response.body().path("data.id");
        System.out.println("ID: " + userId);
        return userId;
    }

    @Step("get data user email from the response")
    public String getUserEmail() {
        Response response = SerenityRest.lastResponse();
        String userEmail = response.body().path("data.email");
        System.out.println("Email: " + userEmail);
        return userEmail;
    }
}