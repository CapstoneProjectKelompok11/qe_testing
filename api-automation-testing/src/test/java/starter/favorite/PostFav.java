package starter.favorite;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PostFav {

    private String token;
    private int buildingId;

    @Step("I set an endpoint for POST favorite")
    public String setPostEndpointFavorite(){
        return base_url + "/auth/building/favorite?buildingId={buildingId}";
    }

    @Step("I request POST detail favorite")
    public void requestPostDetailFavorite(String status, int buildingId) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        this.buildingId = buildingId;

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("buildingId", this.buildingId)
                    .when().post(setPostEndpointFavorite());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("buildingId", this.buildingId)
                    .when().post(setPostEndpointFavorite());
        }
    }

    @Step("I validate the status code for post favorite is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post favorite")
    public void validateDataDetailAfterPostFavorite(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/building/favorite"));
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
