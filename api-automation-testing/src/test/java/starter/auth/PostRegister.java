package starter.auth;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import utils.General;

import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PostRegister {

    private General general = new General();
    private String firstname, lastname, phone, email, password;

    @Step("I set an endpoint for POST register")
    public String setPostEndpointRegister(){
        return base_url + "/login";
    }

    @Step("I request POST detail register")
    public void requestPostDetailRegister(String firstname, String lastname, String phone, String email, String password) throws Exception {
        if (email.equals("new")) {
            this.email = general.randomEmail();
            try (FileWriter file = new FileWriter("src//test//resources//filejson//email.json")){
                file.write(this.email);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (email.equals("existed")) {
            this.email = "user@gmail.com";
        } else {
            this.email = "";
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;

        JSONObject requestData = new JSONObject();
        requestData.put("firstName", this.firstname);
        requestData.put("lastName", this.lastname);
        requestData.put("phone", this.phone);
        requestData.put("email", this.email);
        requestData.put("password", this.password);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpointRegister());
    }

    @Step("I validate the status code is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after register")
    public void validateDataDetailAfterRegister(String message){
        if (message.equals("success")) {
            SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else {
            SerenityRest.then().body("status.code", equalTo("BAD_CREDENTIALS"));
            SerenityRest.then().body("status.message", equalTo("Provided Credentials is wrong!"));
            SerenityRest.then().body("data", equalTo("Email or Password is incorrect"));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//timestamp.json")) {
            file.write(timestamp);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("data");
        System.out.println("Error message: " + errorMessage);
        return errorMessage;
    }
}
