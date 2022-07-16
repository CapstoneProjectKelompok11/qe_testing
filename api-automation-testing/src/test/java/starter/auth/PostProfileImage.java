package starter.auth;

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

public class PostProfileImage {

    private String image, token;

    @Step("I set an endpoint for POST profile image")
    public String setPostEndpointProfileImage(){
        return base_url + "/auth/profile/image";
    }

    @Step("I request POST detail profile image")
    public void requestPostDetailProfileImage(String status, String image) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        if (image.equals("valid format")) {
            this.image = "src/test/resources/image/the_missing_picture.jpg";
        } else if (image.equals("invalid format")){
            this.image = "src/test/resources/filejson/profileImage.json";
        } else {
            this.image = "";
        }

        File file = new File(this.image);

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "multipart/form-data")
                    .multiPart("image", file)
                    .when().post(setPostEndpointProfileImage());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "multipart/form-data")
                    .header("Authorization", "Bearer " + this.token)
                    .multiPart("image", file)
                    .when().post(setPostEndpointProfileImage());
        }
    }

    @Step("I validate the status code for post profile image is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post profile image")
    public void validateDataDetailAfterPostProfileImage(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/profile/image"));
        } else if (message.equals("bad request")){
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        } else {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.id", equalTo(getUserId()));
            SerenityRest.then().body("data.email", equalTo(getUserEmail()));
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

    @Step("get user id from the response")
    public int getUserId() {
        Response response = SerenityRest.lastResponse();
        int userId = response.body().path("data.id");
        System.out.println("ID: " + userId);
        return userId;
    }

    @Step("get data user email from the response")
    public String getUserEmail() {
        Response response = SerenityRest.lastResponse();
        String userEmail = response.body().path("data.email");
        System.out.println("Email: " + userEmail);
        return userEmail;
    }

    @Step("get filename from the response")
    public String getFilename() {
        Response response = SerenityRest.lastResponse();
        String filename = response.body().path("data.image");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//profileImage.json")){
            file.write(filename);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Filename: " + filename);
        return filename;
    }
}
