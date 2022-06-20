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

    @Given("I set an endpoint for GET image")
    public void iSetAnEndpointForGETImage() {
        getFloorImage.setGetEndpointImage();
    }

    @When("I request GET detail image with input {string}")
    public void iRequestGETDetailImageWithInput(String image) throws IOException {
        getFloorImage.requestGetDetailImage(image);
    }

    @Then("I validate the status code for get image is {int}")
    public void iValidateTheStatusCodeForGetImageIsStatusCode(int statusCode) {
        getFloorImage.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details")
    public void validateTheAndDataDetails(String message) {
        getFloorImage.validateTheDataDetails(message);
    }
}
