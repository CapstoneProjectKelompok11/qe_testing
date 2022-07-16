package starter.floor;

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

public class PutFloor {

    private String name, type, floorSize, token;
    private int floorId, maxCapacity, startingPrice;

    @Step("I set an endpoint for PUT floor")
    public String setPUTEndpointFloor(){
        return base_url + "/admin/floor?floorId={floorId}";
    }

    @Step("I request PUT detail floor")
    public void requestPUTDetailFloor(String status, int floorId, String name, String type, String floor_size, int max_capacity, int starting_price) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        this.floorId = floorId;
        this.name = name;
        this.type = type;
        this.floorSize = floor_size;
        this.maxCapacity = max_capacity;
        this.startingPrice = starting_price;

        JSONObject requestData = new JSONObject();
        requestData.put("name", this.name);
        requestData.put("type", this.type);
        requestData.put("floor_size", this.floorSize);
        requestData.put("max_capacity", this.maxCapacity);
        requestData.put("starting_price", this.startingPrice);

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("floorId", this.floorId)
                    .body(requestData.toJSONString())
                    .when().put(setPUTEndpointFloor());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("floorId", this.floorId)
                    .body(requestData.toJSONString())
                    .when().put(setPUTEndpointFloor());
        }
    }

    @Step("I validate the status code for PUT floor is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after PUT floor")
    public void validateDataDetailAfterPUTFloor(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/floor"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.id", equalTo(getDataBuildingID()));
        } else if (message.equals("not found")){
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        } else {
            SerenityRest.then().body("status.code", equalTo("UNKNOWN_ERROR"));
            SerenityRest.then().body("status.message", equalTo("Happened unknown error!"));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get data building id from the response")
    public int getDataBuildingID() {
        Response response = SerenityRest.lastResponse();
        int buildingId = response.body().path("data.id");
        System.out.println("ID: " + buildingId);
        return buildingId;
    }
}
