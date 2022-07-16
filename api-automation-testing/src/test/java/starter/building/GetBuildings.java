package starter.building;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.General.base_url;

public class GetBuildings {

    private int complexId, page, limit;

    @Step("I set an endpoint for GET buildings")
    public String setGetEndpointBuilding() {
        return base_url + "/buildings?complexId={complexId}&page={page}&limit={limit}";
    }

    @Step("I request GET detail buildings")
    public void requestGetDetailBuilding(int complexId, int page, int limit) {
        this.complexId = complexId;
        this.page = page;
        this.limit = limit;

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("complexId", this.complexId)
                .pathParam("page", this.page)
                .pathParam("limit", this.limit)
                .when().get(setGetEndpointBuilding());
    }

    @Step("I validate the status code is for get buildings is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of buildings")
    public void validateTheDataDetailsOfBuilding(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("success")) {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.id", equalTo(getDataID()));
            SerenityRest.then().body("data.name", equalTo(getDataName()));
            SerenityRest.then().body("data.address", equalTo(getDataAddress()));
//            getDataImages();
        } else if (message.equals("error")) {
            SerenityRest.then().body("status.code", equalTo("UNKNOWN_ERROR"));
            SerenityRest.then().body("status.message", equalTo("Happened unknown error!"));
            SerenityRest.then().body("data", equalTo(null));
        } else if (message.equals("data null")) {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else {
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        }
    }

    @Step("get data image for the other request")
    public String getDataImages() {
        Response response = SerenityRest.lastResponse();
        String dataImages = response.body().path("data.images[0].fileName");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//buildingImages.json")) {
            file.write(dataImages);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataImages;
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

    @Step("get data building name from the response")
    public String getDataName() {
        Response response = SerenityRest.lastResponse();
        String buildingName = response.body().path("data.name");
        System.out.println("Building name: " + buildingName);
        return buildingName;
    }

    @Step("get data building address from the response")
    public String getDataAddress() {
        Response response = SerenityRest.lastResponse();
        String buildingAddress = response.body().path("data.address");
        System.out.println("Building address: " + buildingAddress);
        return buildingAddress;
    }
}
