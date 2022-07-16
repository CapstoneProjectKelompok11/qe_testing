package starter.reservation;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PutCancel {

    private String token;
    private int reservationId;

    @Step("I set an endpoint for PUT cancel reservation")
    public String setPUTEndpoint(){
        return base_url + "/auth/reservation/cancel?reservationId={reservationId}";
    }

    @Step("I request PUT detail cancel reservation")
    public void requestPUTDetail(String status, String reservationId) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        if (reservationId.equals("existed")) {
            this.reservationId = Integer.parseInt(FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//reservationId.json"), StandardCharsets.UTF_8));
        } else if (reservationId.equals("not existed")){
            this.reservationId = 99;
        } else {
            this.reservationId = 0;
        }

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("reservationId", this.reservationId)
                    .when().put(setPUTEndpoint());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("reservationId", this.reservationId)
                    .when().put(setPUTEndpoint());
        }
    }

    @Step("I validate the status code for PUT cancel reservation is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after PUT cancel reservation")
    public void validateDataDetail(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/reservation/cancel"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else {
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
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
}
