package starter.reservation;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PostPayment {

    private String image, token;
    private int reservationId;

    @Step("I set an endpoint for POST payment reservation")
    public String setPostEndpoint(){
        return base_url + "/auth/reservation/payment?reservationId={reservationId}";
    }

    @Step("I request POST detail payment reservation")
    public void requestPostDetail(String status, String reservationId, String image) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        if (reservationId.equals("existed")) {
            this.reservationId = Integer.parseInt(FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//reservationId.json"), StandardCharsets.UTF_8));
        } else if (reservationId.equals("not existed")){
            this.reservationId = 99;
        } else {
            this.reservationId = 0;
        }
        if (image.equals("valid format")) {
            this.image = "src/test/resources/image/bukti_transfer.jpg";
        } else if (image.equals("invalid format")){
            this.image = "src/test/resources/image/bukti_payment.png";
        } else {
            this.image = "";
        }

        File file = new File(this.image);

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("reservationId", this.reservationId)
                    .multiPart("image", file)
                    .when().post(setPostEndpoint());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "multipart/form-data")
                    .pathParam("reservationId", this.reservationId)
                    .multiPart("image", file)
                    .when().post(setPostEndpoint());
        }
    }

    @Step("I validate the status code for post payment reservation is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post payment reservation")
    public void validateDataDetail(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/reservation/payment"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.image", equalTo(getFilename()));
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

    @Step("get filename from the response")
    public String getFilename() {
        Response response = SerenityRest.lastResponse();
        String filename = response.body().path("data.image");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//paymentImage.json")){
            file.write(filename);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Filename: " + filename);
        return filename;
    }
}
