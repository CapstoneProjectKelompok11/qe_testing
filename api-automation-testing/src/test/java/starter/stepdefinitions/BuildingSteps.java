package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.building.GetBuilding;
import starter.building.GetBuildingImage;
import starter.floor.GetFloor;
import starter.floor.GetFloorImage;

import java.io.IOException;

public class BuildingSteps {

    @Steps
    GetBuilding getBuilding;

    @Steps
    GetBuildingImage getBuildingImage;

    @Given("I set an endpoint for GET building")
    public void iSetAnEndpointForGETBuilding() {
        getBuilding.setGetEndpointBuilding();
    }

    @When("I request GET detail building with input {int}")
    public void iRequestGETDetailBuildingWithInputId(int id) {
        getBuilding.requestGetDetailBuilding(id);
    }

    @Then("I validate the status code for get building is {int}")
    public void iValidateTheStatusCodeForGetBuildingIsStatusCode(int statusCode) {
        getBuilding.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of building")
    public void validateTheAndDataDetailsOfBuilding(String message) {
        getBuilding.validateTheDataDetailsOfBuilding(message);
    }

    @Given("I set an endpoint for GET building image")
    public void iSetAnEndpointForGETBuildingImage() {
        getBuildingImage.setGetEndpointBuildingImage();
    }

    @When("I request GET detail building image with input {string}")
    public void iRequestGETDetailBuildingImageWithInput(String filename) throws IOException {
        getBuildingImage.requestGetDetailBuildingImage(filename);
    }

    @Then("I validate the status code for get building image is {int}")
    public void iValidateTheStatusCodeForGetBuildingImageIsStatusCode(int statusCode) {
        getBuildingImage.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data building image")
    public void validateTheAndDataBuildingImage(String message) {
        getBuildingImage.validateTheDataDetailsOfBuildingImage(message);
    }
}
