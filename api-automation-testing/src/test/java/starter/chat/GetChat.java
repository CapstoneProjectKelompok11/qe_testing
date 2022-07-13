package starter.chat;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class GetChat {

    private String token;

    @Step("I set an endpoint for GET chat")
    public String setGetEndpointChat() {
        return base_url + "/auth/chat/list";
    }

    @Step("I request GET detail chat")
    public void requestGetDetailChat() throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token)
                .when().get(setGetEndpointChat());
    }

    @Step("I validate the status code is for get list chat is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of chat")
    public void validateTheDataDetailsOfChat() {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        SerenityRest.then().body("status.code", equalTo("SUCCESS"));
        SerenityRest.then().body("status.message", equalTo("Success!"));
//        SerenityRest.then().body("data", equalTo(null));
    }

    @Step("get timestamp from the response")
    public String getTimestamp() {
        Response response = SerenityRest.lastResponse();
        String timestamp = response.body().path("timestamp");
        System.out.println("Timestamp: " + timestamp);
        return timestamp;
    }
}
