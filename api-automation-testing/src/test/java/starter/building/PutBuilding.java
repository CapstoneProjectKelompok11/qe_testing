package starter.building;

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

public class PutBuilding {

    private String name, address, desc, buildingSize, token;
    private int buildingId, capacity;

    @Step("I set an endpoint for PUT building")
    public String setPUTEndpointBuilding(){
        return base_url + "/admin/building?buildingId={buildingId}";
    }

    @Step("I request PUT detail building")
    public void requestPUTDetailBuilding(String status, int buildingId, String name, String address, String description, String building_size, int capacity) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        this.buildingId = buildingId;
        if (name.equals("new")) {
            this.name = "Kalibata City Tower Tulip";
        } else {
            this.name = name;
        }
        if (address.equals("existed")) {
            this.address = "SCBD Lot 9 Jl. Jenderal Sudirman Kav. 52-53, RT.5/RW.3, Senayan";
        } else if (address.equals("new")) {
            this.address = "Jl. Raya Kalibata No.60, RT.9/RW.4, Rawajati, Kec. Pancoran, Kota Jakarta Selatan";
        } else {
            this.address = address;
        }
        if (description.equals("existed")) {
            this.desc = "description";
        } else if (description.equals("new")){
            this.desc = "new description";
        } else {
            this.desc = description;
        }
        if (building_size.equals("existed")) {
            this.buildingSize = "290 x 290 m2";
        } else if (building_size.equals("new")){
            this.buildingSize = "350 x 200 m2";
        } else {
            this.buildingSize = building_size;
        }
        this.capacity = capacity;

        JSONObject requestData = new JSONObject();
        requestData.put("name", this.name);
        requestData.put("address", this.address);
        requestData.put("description", this.desc);
        requestData.put("building_size", this.buildingSize);
        requestData.put("capacity", this.capacity);

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("buildingId", this.buildingId)
                    .body(requestData.toJSONString())
                    .when().put(setPUTEndpointBuilding());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("buildingId", this.buildingId)
                    .body(requestData.toJSONString())
                    .when().put(setPUTEndpointBuilding());
        }
    }

    @Step("I validate the status code for PUT building is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after PUT building")
    public void validateDataDetailAfterPUTBuilding(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/building"));
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
