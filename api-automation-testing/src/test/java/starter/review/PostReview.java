package starter.review;

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

public class PostReview {

    private String review, token;
    private int buildingId, rating;

    @Step("I set an endpoint for POST review")
    public String setPostEndpointReview(){
        return base_url + "/auth/review?buildingId={buildingId}";
    }

    @Step("I request POST detail review")
    public void requestPostDetailReview(String statusReview, String status, int buildingId, String review, int rating) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
        if (review.equals("good")) {
            this.review = "Fasilitas yang diberikan sangat bagus";
        } else if (review.equals("bad")) {
            this.review = "Pelayanannya tidak ramah";
        } else {
            this.review = "";
        }
        if (statusReview.equals("existed") && (buildingId < 5)) {
            this.buildingId = buildingId + 1;
        } else if (statusReview.equals("existed") && (buildingId > 5)) {
            this.buildingId = buildingId - 1;
        } else {
            this.buildingId = buildingId;
        }
        this.rating = rating;

        JSONObject requestData = new JSONObject();
        requestData.put("review", this.review);
        requestData.put("rating", this.rating);

        if (status.equals("not authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("buildingId", this.buildingId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpointReview());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("buildingId", this.buildingId)
                    .body(requestData.toJSONString())
                    .when().post(setPostEndpointReview());
        }
    }

    @Step("I validate the status code for post review is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after post review")
    public void validateDataDetailAfterPostReview(String message){
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("unauthorized")) {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/auth/review"));
        } else if (message.equals("success")){
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data.user.id", equalTo(getDataUserID()));
            SerenityRest.then().body("data.user.email", equalTo(getDataUserEmail()));
            SerenityRest.then().body("data.review", equalTo(this.review));
            SerenityRest.then().body("data.review_date", equalTo(getDataReviewDate()));
        } else if (message.equals("not found")){
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
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

    @Step("get data user id from the response")
    public int getDataUserID() {
        Response response = SerenityRest.lastResponse();
        int userId = response.body().path("data.user.id");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//userId.json")){
            file.write(userId);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ID: " + userId);
        return userId;
    }

    @Step("get data user email from the response")
    public String getDataUserEmail() {
        Response response = SerenityRest.lastResponse();
        String userEmail = response.body().path("data.user.email");
        System.out.println("Email: " + userEmail);
        return userEmail;
    }

    @Step("get data review date from the response")
    public String getDataReviewDate() {
        Response response = SerenityRest.lastResponse();
        String reviewDate = response.body().path("data.review_date");
        System.out.println("Review date: " + reviewDate);
        return reviewDate;
    }
}
