package starter.auth;

import utils.General;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;

public class PostLogin {

    private General general = new General();
    private String email, password;

    @Step("I set an endpoint for POST login")
    public String setPostEndpointLogin(){
        return base_url + "/login";
    }

    @Step("I request POST detail login")
    public void requestPostDetailLogin(String email, String password) throws Exception {
        if (email.equals("existed")) {
            this.email = "user@gmail.com";
            // this.email = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//email.json"), StandardCharsets.UTF_8);
        } else if (email.equals("not existed")) {
            this.email = general.randomEmail();
        } else {
            this.email = email;
        }
        this.password = password;

        JSONObject requestData = new JSONObject();
        requestData.put("email", this.email);
        requestData.put("password", this.password);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpointLogin());
    }

    @Step("I validate the status code for login is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after login")
    public void validateDataDetailAfterLogin(String message){
        if (message.equals("success")) {
            SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.token", equalTo(getToken()));
        } else {
            SerenityRest.then().body("data", equalTo("Email or Password is incorrect"));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get token from the response")
    public String getToken() {
        Response response = SerenityRest.lastResponse();
        String token = response.body().path("data.token");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//token.json")) {
            file.write(token);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Token: " + token);
        return token;
    }

    @Step("get admin token from the response")
    public String getAdminToken() {
        Response response = SerenityRest.lastResponse();
        String adminToken = response.body().path("data.token");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//adminToken.json")) {
            file.write(adminToken);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Admin token: " + adminToken);
        return adminToken;
    }
}
