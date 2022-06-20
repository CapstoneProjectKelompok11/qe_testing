package starter.complex;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class GetComplex {

    private String city;

    @Step("I set an endpoint for GET all complex")
    public String setGetEndpointComplex(String city) {
        if (city.equals("")) {
            return base_url + "/complex";
        } else {
            return base_url + "/complex?city={city}";
        }
    }

    @Step("I request GET detail complex")
    public void requestGetDetailComplex(String city) {
        if (city.equals("")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .when().get(setGetEndpointComplex(city));
        } else {
            this.city = city;
            SerenityRest.given()
                    .pathParam("city", this.city)
                    .header("Content-Type", "application/json")
                    .when().get(setGetEndpointComplex(city));
        }
    }

    @Step("I validate the status code is for get all complex is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of complex")
    public void validateTheDataDetailsOfComplex(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        SerenityRest.then().body("status.code", equalTo("SUCCESS"));
        SerenityRest.then().body("status.message", equalTo("Success!"));
        if (message.equals("existed")) {
            SerenityRest.then().body("data[0].id", equalTo(getDataID()));
            SerenityRest.then().body("data[0].complex_name", equalTo(getDataName()));
            SerenityRest.then().body("data[0].city.id", equalTo(getDataCityID()));
            SerenityRest.then().body("data[0].city.city_name", equalTo(this.city));
        } else if (message.equals("get all data")) {
            SerenityRest.then().body("data[0].id", equalTo(getDataID()));
            SerenityRest.then().body("data[0].complex_name", equalTo(getDataName()));
            SerenityRest.then().body("data[0].city.id", equalTo(getDataCityID()));
            SerenityRest.then().body("data[0].city.city_name", equalTo(getDataCityName()));
        }
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

    @Step("get first data complex name from the response")
    public String getDataName() {
        Response response = SerenityRest.lastResponse();
        String complexName = response.body().path("data[0].complex_name");
        System.out.println("Complex name: " + complexName);
        return complexName;
    }

    @Step("get first data city id from the response")
    public int getDataCityID() {
        Response response = SerenityRest.lastResponse();
        int cityId = response.body().path("data[0].city.id");
        System.out.println("City ID: " + cityId);
        return cityId;
    }

    @Step("get first data city name from the response")
    public String getDataCityName() {
        Response response = SerenityRest.lastResponse();
        String cityName = response.body().path("data[0].city.city_name");
        System.out.println("City name: " + cityName);
        return cityName;
    }
}
