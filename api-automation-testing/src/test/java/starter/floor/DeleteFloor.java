package starter.floor;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class DeleteFloor {

    private String token;
    private int floorId;

    @Step("I set an endpoint for DELETE floor")
    public String setDeleteEndpointFloor(){
        return base_url + "/admin/floor?floorId={floorId}";
    }

    @Step("I request DELETE detail floor")
    public void requestDeleteDetailFloor(String status, int floorId) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        this.floorId = floorId;

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("floorId", this.floorId)
                    .when().delete(setDeleteEndpointFloor());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("floorId", this.floorId)
                    .when().delete(setDeleteEndpointFloor());
        }
    }

    @Step("I validate the status code for delete floor is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after delete floor")
    public void validateDataDetailAfterDeleteFloor(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/floor"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else {
            SerenityRest.then().body("status.code", equalTo(getCode()));
            SerenityRest.then().body("status.message", equalTo(getMessage()));
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

    @Step("get code from the response")
    public String getCode() {
        Response response = SerenityRest.lastResponse();
        String code = response.body().path("status.code");
        System.out.println("Code: " + code);
        return code;
    }

    @Step("get message from the response")
    public String getMessage() {
        Response response = SerenityRest.lastResponse();
        String message = response.body().path("status.message");
        System.out.println("Message: " + message);
        return message;
    }
}
