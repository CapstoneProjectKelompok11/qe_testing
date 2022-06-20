package starter.city;

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

public class PostCity {

    private String city, token;

    @Step("I set an endpoint for POST city")
    public String setPostEndpointCity(){
        return base_url + "/admin/city";
    }

    @Step("I request POST detail city")
    public void requestPostDetailCity(String status, String city) throws IOException {
        this.city = city;
        if (status.equals("not authorized")) {
            JSONObject requestData = new JSONObject();
            requestData.put("city_name", this.city);

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpointCity());
        } else {
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
            JSONObject requestData = new JSONObject();
            requestData.put("city_name", this.city);

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpointCity());
        }
    }

    @Step("I validate the status code for post city is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post city")
    public void validateDataDetailAfterPostCity(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/city"));
        } else {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.id", equalTo(getDataID()));
            SerenityRest.then().body("data.city_name", equalTo(this.city));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get data id from the response")
    public int getDataID() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data.id");
        System.out.println("ID: " + id);
        return id;
    }
}
