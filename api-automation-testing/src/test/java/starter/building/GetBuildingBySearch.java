package starter.building;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.General.base_url;

public class GetBuildingBySearch {

    private String name;

    @Step("I set an endpoint for GET building by search")
    public String setGetEndpointBuilding() {
        return base_url + "/building/search?name={name}";
    }

    @Step("I request GET detail building by search")
    public void requestGetDetailBuilding(String name) {
        this.name = name;
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("name", this.name)
                .when().get(setGetEndpointBuilding());
    }

    @Step("I validate the status code is for get building by search is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of building by search")
    public void validateTheDataDetailsOfBuilding(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        SerenityRest.then().body("status.code", equalTo("SUCCESS"));
        SerenityRest.then().body("status.message", equalTo("Success!"));
        if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data[0].id", equalTo(getDataID()));
            SerenityRest.then().body("data[0].name", equalTo(getDataName()));
            SerenityRest.then().body("data[0].address", equalTo(getDataAddress()));
            getDataImages();
        }
    }

    @Step("get data image for the other request")
    public String getDataImages() {
        Response response = SerenityRest.lastResponse();
        String dataImages = response.body().path("data[0].images[0].fileName");
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
        int id = response.body().path("data[0].id");
        System.out.println("ID: " + id);
        return id;
    }

    @Step("get data building name from the response")
    public String getDataName() {
        Response response = SerenityRest.lastResponse();
        String buildingName = response.body().path("data[0].name");
        System.out.println("Building name: " + buildingName);
        return buildingName;
    }

    @Step("get data building address from the response")
    public String getDataAddress() {
        Response response = SerenityRest.lastResponse();
        String buildingAddress = response.body().path("data[0].address");
        System.out.println("Building address: " + buildingAddress);
        return buildingAddress;
    }
}
