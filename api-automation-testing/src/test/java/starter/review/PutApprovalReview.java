package starter.review;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static utils.General.base_url;

public class PutApprovalReview {

    private String token;
    private int userId, buildingId;
    private boolean approved;

    @Step("I set an endpoint for PUT approval review")
    public String setPUTEndpointApprovalReview() {
        return base_url + "/admin/review?userId={userId}&buildingId={buildingId}&approved={approved}";
    }

    @Step("I request PUT approval review")
    public void requestPUTApprovalReview(String status, String userId, int buildingId, String approved) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//adminToken.json"), StandardCharsets.UTF_8);
        if (userId.equals("existed")) {
            this.userId = Integer.parseInt(FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//userId.json"), StandardCharsets.UTF_8));
        } else {
            this.userId = 0;
        }
        this.buildingId = buildingId;
        if (approved.equals("true")) {
            this.approved = true;
        } else {
            this.approved = false;
        }

        if (status.equals("authorized")) {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .pathParam("userId", this.userId)
                    .pathParam("buildingId", this.userId)
                    .pathParam("approved", this.approved)
                    .when().put(setPUTEndpointApprovalReview());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("userId", this.userId)
                    .pathParam("buildingId", this.userId)
                    .pathParam("approved", this.approved)
                    .when().put(setPUTEndpointApprovalReview());
        }
    }

    @Step("I validate the status code is for get review is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details of review")
    public void validateTheDataDetailsOfReview(String message) {
        SerenityRest.then().body("timestamp", equalTo(getTimestamp()));
        if (message.equals("success")) {
            SerenityRest.then().body("status.code", equalTo("SUCCESS"));
            SerenityRest.then().body("status.message", equalTo("Success!"));
            SerenityRest.then().body("data[0].user.id", equalTo(getDataUserID()));
        } else if (message.equals("not found")){
            SerenityRest.then().body("status.code", equalTo("DATA_NOT_FOUND"));
            SerenityRest.then().body("status.message", equalTo("Data not found!"));
            SerenityRest.then().body("data", equalTo(null));
        } else {
            SerenityRest.then().body("status", equalTo(403));
            SerenityRest.then().body("error", equalTo("Forbidden"));
            SerenityRest.then().body("path", equalTo("/api/admin/review"));
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
        int userId = response.body().path("data[0].user.id");
        System.out.println("ID: " + userId);
        return userId;
    }
}
