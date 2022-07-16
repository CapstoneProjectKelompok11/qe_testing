package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.floor.*;

import java.io.IOException;

public class FloorSteps {

    @Steps
    PutFloor putFloor;

    @Steps
    PostFloor postFloor;

    @Steps
    DeleteFloor deleteFloor;

    @Steps
    PostFloorImage postFloorImage;

    @Steps
    GetFloor getFloor;

    @Steps
    GetFloorImage getFloorImage;

    @Given("I set an endpoint for PUT edit floor")
    public void iSetAnEndpointForPUTEditFloor() {
        putFloor.setPUTEndpointFloor();
    }

    @When("I request PUT detail edit floor with {string}, input {int}, {string}, {string}, {string}, {int}, and {int}")
    public void iRequestPUTDetailEditFloorWithInput(String status, int floorId, String name, String type, String floorSize, int maxCapacity, int startingPrice) throws IOException {
        putFloor.requestPUTDetailFloor(status, floorId, name, type, floorSize, maxCapacity, startingPrice);
    }

    @Then("I validate the status code for put edit floor is {int}")
    public void iValidateTheStatusCodeForPutEditFloorIsStatusCode(int statusCode) {
        putFloor.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after edit floor")
    public void validateTheAndDataDetailsAfterEditFloor(String message) {
        putFloor.validateDataDetailAfterPUTFloor(message);
    }

    @Given("I set an endpoint for POST floor")
    public void iSetAnEndpointForPOSTFloor() {
        postFloor.setPostEndpointFloor();
    }

    @When("I request POST detail floor with {string}, input {int}, {string}, {string}, {string}, {int}, and {int}")
    public void iRequestPOSTDetailFloorWithInputAnd(String status, int buildingId, String name, String type, String floor_size, int max_capacity, int starting_price) throws IOException {
        postFloor.requestPostDetailFloor(status, buildingId, name, type, floor_size, max_capacity, starting_price);
    }

    @Then("I validate the status code for post floor is {int}")
    public void iValidateTheStatusCodeForPostFloorIsStatusCode(int statusCode) {
        postFloor.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post floor")
    public void validateTheAndDataDetailsAfterPostFloor(String message) {
        postFloor.validateDataDetailAfterPostFloor(message);
    }

    @Given("I set an endpoint for DELETE floor")
    public void iSetAnEndpointForDELETEFloor() {
        deleteFloor.setDeleteEndpointFloor();
    }

    @When("I request DELETE detail floor with {string} and input {int}")
    public void iRequestDELETEDetailFloorWithAndInputFloorId(String status, int floorId) throws IOException {
        deleteFloor.requestDeleteDetailFloor(status, floorId);
    }

    @Then("I validate the status code for delete floor is {int}")
    public void iValidateTheStatusCodeForDeleteFloorIsStatusCode(int statusCode) {
        deleteFloor.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after delete floor")
    public void validateTheAndDataDetailsAfterDeleteFloor(String message) {
        deleteFloor.validateDataDetailAfterDeleteFloor(message);
    }

    @Given("I set an endpoint for POST floor image")
    public void iSetAnEndpointForPOSTFloorImage() {
        postFloorImage.setPostEndpointFloorImage();
    }

    @When("I request POST detail floor image with {string}, input {int}, and {string}")
    public void iRequestPOSTDetailFloorImageWithInputFloorIdAnd(String status, int floorId, String image) throws IOException {
        postFloorImage.requestPostDetailFloorImage(status, floorId, image);
    }

    @Then("I validate the status code for post floor image is {int}")
    public void iValidateTheStatusCodeForPostFloorImageIsStatusCode(int statusCode) {
        postFloorImage.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post floor image")
    public void validateTheAndDataDetailsAfterPostFloorImage(String message) {
        postFloorImage.validateDataDetailAfterPostFloorImage(message);
    }

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
}
