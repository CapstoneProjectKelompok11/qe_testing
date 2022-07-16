package starter.floor;

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

public class PostFloorImage {

    private String image, token;
    private int floorId;

    @Step("I set an endpoint for POST floor image")
    public String setPostEndpointFloorImage(){
        return base_url + "/admin/floor/image?floorId={floorId}";
    }

    @Step("I request POST detail floor image")
    public void requestPostDetailFloorImage(String status, int floorId, String image) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        this.floorId = floorId;

        if (image.equals("valid format")) {
            this.image = "src/test/resources/image/floor1.png";
        } else if (image.equals("invalid format")){
            this.image = "src/test/resources/filejson/floorImage.json";
        } else {
            this.image = "";
        }

        File file = new File(this.image);

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "multipart/form-data")
                    .pathParam("floorId", this.floorId)
                    .multiPart("image", file)
                    .when().post(setPostEndpointFloorImage());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("floorId", this.floorId)
                    .multiPart("image", file)
                    .when().post(setPostEndpointFloorImage());
        }
    }

    @Step("I validate the status code for post floor image is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post floor image")
    public void validateDataDetailAfterPostFloorImage(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/floor/image"));
        } else if (message.equals("not found")){
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        } else {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.image", equalTo(getFilename()));
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
        try (FileWriter file = new FileWriter("src//test//resources//filejson//floorImages.json")){
            file.write(filename);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ID: " + filename);
        return filename;
    }
}
