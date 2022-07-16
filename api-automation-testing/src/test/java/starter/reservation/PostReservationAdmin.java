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

public class PostReservationAdmin {

    private String startReserv, company, phone, note, token;
    private int reservationId, floorId, price, participant;

    @Step("I set an endpoint for POST reservation admin")
    public String setPostEndpoint(){
        return base_url + "/admin/reservation?reservationId={reservationId}&floorId={floorId}";
    }

    @Step("I request POST detail reservation admin")
    public void requestPostDetail(String status, String reservationId, int floorId, String startReservation, String company, int price, String phone, int participant, String note) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        if (reservationId.equals("existed")) {
            this.reservationId = Integer.parseInt(FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//reservationId.json"), StandardCharsets.UTF_8));
        } else if (reservationId.equals("not existed")) {
            this.reservationId = 99;
        } else {
            this.reservationId = 0;
        }
        this.floorId = floorId;
        if (startReservation.equals("valid format")) {
            this.startReserv = "15-07-2022 11:25:33";
        } else if (startReservation.equals("invalid format")){
            this.startReserv = "15-07-2022";
        } else {
            this.startReserv = "";
        }
        this.company = company;
        this.price = price;
        this.phone = phone;
        this.participant = participant;
        this.note = note;

        JSONObject requestData = new JSONObject();
        requestData.put("start_reservation", this.startReserv);
        requestData.put("company", this.company);
        requestData.put("price", this.price);
        requestData.put("phone", this.phone);
        requestData.put("participant", this.participant);
        requestData.put("note", this.note);

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("reservationId", this.reservationId)
                    .pathParam("floorId", this.floorId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpoint());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("reservationId", this.reservationId)
                    .pathParam("floorId", this.floorId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpoint());
        }
    }

    @Step("I validate the status code for post reservation admin is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post reservation admin")
    public void validateDataDetail(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/reservation"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
        } else if (message.equals("not found")){
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        } else if (message.equals("bad request")) {
            SerenityRest.then().body("status", equalTo(400));
            SerenityRest.then().body("error", equalTo("Bad Request"));
            SerenityRest.then().body("path", equalTo("/api/admin/reservation"));
        } else {
            SerenityRest.then().body("status.code", equalTo("UNKNOWN_ERROR"));
            SerenityRest.then().body("status.message", equalTo("Happened unknown error!"));
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
            file.write(reservationId);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ID: " + reservationId);
        return reservationId;
    }
}
