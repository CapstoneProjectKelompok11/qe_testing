package starter.floor;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class GetFloor {

    private int buildingId;

    @Step("I set an endpoint for GET floor")
    public String setGetEndpointFloor() {
        return base_url + "/floor?buildingId={buildingId}";
    }

    @Step("I request GET detail floor")
    public void requestGetDetailFloor(int buildingId) {
        this.buildingId = buildingId;
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("buildingId", this.buildingId)
                .when().get(setGetEndpointFloor());
    }

    @Step("I validate the status code is for get floor is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of floor")
    public void validateTheDataDetailsOfFloor(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("success")) {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data[0].id", equalTo(getDataID()));
            SerenityRest.then().body("data[0].name", equalTo(getDataName()));
            getDataImage();
        } else {
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        }
    }

    @Step("get data image for the other request")
    public String getDataImage() {
        Response response = SerenityRest.lastResponse();
        String dataImage = response.body().path("data[0].image");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//dataImage.json")) {
            file.write(dataImage);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Image: " + dataImage);
        return dataImage;
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get first data id from the response")
    public int getDataID() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data[0].id");
        System.out.println("ID: " + id);
        return id;
    }

    @Step("get first data floor name from the response")
    public String getDataName() {
        Response response = SerenityRest.lastResponse();
        String floorName = response.body().path("data[0].name");
        System.out.println("Floor name: " + floorName);
        return floorName;
    }
}
