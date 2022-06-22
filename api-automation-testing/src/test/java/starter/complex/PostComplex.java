package starter.complex;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PostComplex {

    private String complex, token;
    private int cityId;

    @Step("I set an endpoint for POST complex")
    public String setPostEndpointComplex(){
        return base_url + "/admin/complex?cityId={cityId}";
    }

    @Step("I request POST detail complex")
    public void requestPostDetailComplex(String status, String complex, int cityId) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        this.cityId = cityId;
        this.complex = complex;

        JSONObject requestData = new JSONObject();
        requestData.put("complex_name", this.complex);

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("cityId", this.cityId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpointComplex());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("cityId", this.cityId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpointComplex());
        }
    }

    @Step("I validate the status code for post complex is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post complex")
    public void validateDataDetailAfterPostComplex(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/complex"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo(getStatusCode()));
            SerenityRest.then().body("status.message", equalTo(getStatusMessage()));
            SerenityRest.then().body("data.id", equalTo(getDataID()));
            SerenityRest.then().body("data.complex_name", equalTo(this.complex));
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

    @Step("get data id from the response")
    public int getDataID() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data.id");
        System.out.println("ID: " + id);
        return id;
    }
}
