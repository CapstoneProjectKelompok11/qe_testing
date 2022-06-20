package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.floor.GetFloor;
import starter.floor.GetFloorImage;

import java.io.IOException;

public class FloorSteps {

    @Steps
    GetFloor getFloor;

    @Steps
    GetFloorImage getFloorImage;

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
