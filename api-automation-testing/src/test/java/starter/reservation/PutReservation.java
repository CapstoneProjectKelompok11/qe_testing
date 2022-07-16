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

public class PutReservation {

    private String status, token;
    private int reservationId;

    @Step("I set an endpoint for PUT edit status reservation")
    public String setPUTEndpoint(){
        return base_url + "/admin/reservation?reservationId={reservationId}&status={status}";
    }

    @Step("I request PUT detail edit status reservation")
    public void requestPUTDetail(String statusAuth, String token, String reservationId, String status) throws IOException {
        if (token.equals("valid")) {
            this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        } else if (token.equals("invalid")){
            this.token = "abc";
        } else {
            this.token = "";
        }
        if (reservationId.equals("existed")) {
            this.reservationId = Integer.parseInt(FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//reservationId.json"), StandardCharsets.UTF_8));
        } else if (reservationId.equals("not existed")){
            this.reservationId = 99;
        } else {
            this.reservationId = 0;
        }
        this.status = status;

        if (statusAuth.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("reservationId", this.reservationId)
                    .pathParam("status", this.status)
                    .when().put(setPUTEndpoint());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("reservationId", this.reservationId)
                    .pathParam("status", this.status)
                    .when().put(setPUTEndpoint());
        }
    }

    @Step("I validate the status code for PUT edit status reservation is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after PUT edit status reservation")
    public void validateDataDetail(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/reservation"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else if (message.equals("bad request")) {
            SerenityRest.then().body("status", equalTo(400));
            SerenityRest.then().body("error", equalTo("Bad Request"));
            SerenityRest.then().body("path", equalTo("/api/admin/reservation"));
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
