package starter.reservation;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PostReservationUser {

    private String startReserv, company, phone, note, token;
    private int floorId, participant;

    @Step("I set an endpoint for POST reservation user")
    public String setPostEndpoint(){
        return base_url + "/auth/reservation?floorId={floorId}";
    }

    @Step("I request POST detail reservation user")
    public void requestPostDetail(String status, int floorId, String startReservation, String company, String phone, int participant, String note) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        this.floorId = floorId;
        if (startReservation.equals("existed")) {
            this.startReserv = "10-07-2022 08:33:20";
        } else {
            this.startReserv = "15-07-2022 11:25:33";
        }
        this.company = company;
        if (phone.equals("existed")) {
            this.phone = "08248435468";
        } else {
            this.phone = "081239283744";
        }
        this.participant = participant;
        this.note = note;


        JSONObject requestData = new JSONObject();
        requestData.put("start_reservation", this.startReserv);
        requestData.put("company", this.company);
        requestData.put("phone", this.phone);
        requestData.put("participant", this.participant);
        requestData.put("note", this.note);

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("floorId", this.floorId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpoint());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("floorId", this.floorId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpoint());
        }
    }

    @Step("I validate the status code for post reservation user is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post reservation user")
    public void validateDataDetail(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/reservation"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.id", equalTo(getReservationID()));
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

    @Step("get data reservation id from the response")
    public int getReservationID() {
        Response response = SerenityRest.lastResponse();
        int reservationId = response.body().path("data.id");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//reservationId.json")){
            file.write(String.valueOf(reservationId));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ID: " + reservationId);
        return reservationId;
    }
}
