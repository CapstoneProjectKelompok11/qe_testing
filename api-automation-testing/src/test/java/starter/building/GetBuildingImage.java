package starter.building;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.General.base_url;

public class GetBuildingImage {

    private String filename;

    @Step("I set an endpoint for GET building image")
    public String setGetEndpointBuildingImage() {
        return base_url + "/building/image/{filename}";
    }

    @Step("I request GET detail building image")
    public void requestGetDetailBuildingImage(String filename) throws IOException {
        if (filename.equals("valid")) {
            this.filename = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//buildingImages.json"), StandardCharsets.UTF_8);
        } else if (filename.equals("invalid")) {
            this.filename = "abc";
        } else {
            this.filename = "";
        }

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("filename", this.filename)
                .when().get(setGetEndpointBuildingImage());
    }

    @Step("I validate the status code is for get building image is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of building image")
    public void validateTheDataDetailsOfBuildingImage(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body(notNullValue());
        } else if (message.equals("not found")) {
            SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
            SerenityRest.then().body("status", equalTo(404));
            SerenityRest.then().body("error", equalTo("Not Found"));
            SerenityRest.then().body("path", equalTo(getPath()));
        } else {
            SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
            SerenityRest.then().body("status", equalTo(500));
            SerenityRest.then().body("error", equalTo("Internal Server Error"));
            SerenityRest.then().body("path", equalTo(getPath()));
        }
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }

    @Step("get path from the response")
    public String getPath() {
        Response response = SerenityRest.lastResponse();
        String path = response.body().path("path");
        System.out.println("Path: " + path);
        return path;
    }
}
