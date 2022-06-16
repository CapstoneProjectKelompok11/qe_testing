package starter.city;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class GetCity {

    @Step("I set an endpoint for GET city")
    public String setGetEndpointCity() {
        return base_url + "/city";
    }

    @Step("I request GET detail city")
    public void requestGetDetailCity() {
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .when().get(setGetEndpointCity());
    }

    @Step("I validate the status code is for get all city is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of city")
    public void validateTheDataDetailsOfCity() {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        SerenityRest.then().body("status.code", equalTo("SUCCESS"));
        SerenityRest.then().body("status.message", equalTo("Success!"));
        SerenityRest.then().body("data[0].id", equalTo(getDataID()));
        SerenityRest.then().body("data[0].city_name", equalTo(getDataName()));
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

    @Step("get first data city name from the response")
    public String getDataName() {
        Response response = SerenityRest.lastResponse();
        String cityName = response.body().path("data[0].city_name");
        System.out.println("City name: " + cityName);
        return cityName;
    }
}
