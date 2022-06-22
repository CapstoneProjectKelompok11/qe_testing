package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.floor.GetFloor;
import starter.floor.GetFloorImage;
import starter.floor.PostFloor;

import java.io.IOException;

public class FloorSteps {

    @Steps
    GetFloor getFloor;

    @Steps
    GetFloorImage getFloorImage;

    @Steps
    PostFloor postFloor;

    @Given("I set an endpoint for GET floor")
    public void iSetAnEndpointForGETFloor() {
        getFloor.setGetEndpointFloor();
    }

    @When("I request GET detail floor with input {int}")
    public void iRequestGETDetailFloorWithInput(int buildingId) {
        getFloor.requestGetDetailFloor(buildingId);
    }

    @Then("I validate the status code for get floor is {int}")
    public void iValidateTheStatusCodeForGetFloorIs(int statusCode) {
        getFloor.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of floor")
    public void validateTheAndDataDetailsOfFloor(String message) {
        getFloor.validateTheDataDetailsOfFloor(message);
    }

    @Given("I set an endpoint for GET floor image")
    public void iSetAnEndpointForGETFloorImage() {
        getFloorImage.setGetEndpointImage();
    }

    @When("I request GET detail floor image with input {string}")
    public void iRequestGETDetailFloorImageWithInput(String filename) throws IOException {
        getFloorImage.requestGetDetailImage(filename);
    }

    @Then("I validate the status code for get floor image is {int}")
    public void iValidateTheStatusCodeForGetFloorImageIsStatusCode(int statusCode) {
        getFloorImage.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data floor image")
    public void validateTheAndDataFloorImage(String message) {
        getFloorImage.validateTheDataDetailsOfFloorImage(message);
    }

    @Given("I set an endpoint for POST floor")
    public void iSetAnEndpointForPOSTFloor() {
        postFloor.setPostEndpointFloor();
    }

    @When("I request POST detail floor with {string}, input {int}, {string}, {string}, {string}, {int}, {int} and {string}")
    public void iRequestPOSTDetailFloorWithInputAnd(String status, int buildingId, String name, String type, String floor_size, int max_capacity, int starting_price, String facilities) throws IOException {
        postFloor.requestPostDetailFloor(status, buildingId, name, type, floor_size, max_capacity, starting_price, facilities);
    }

    @Then("I validate the status code for post floor is {int}")
    public void iValidateTheStatusCodeForPostFloorIsStatusCode(int statusCode) {
        postFloor.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post floor")
    public void validateTheAndDataDetailsAfterPostFloor(String message) {
        postFloor.validateDataDetailAfterPostFloor(message);
    }
}
