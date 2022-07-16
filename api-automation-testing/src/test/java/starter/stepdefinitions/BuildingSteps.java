package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.building.*;

import java.io.IOException;

public class BuildingSteps {

    @Steps
    PutBuilding putBuilding;

    @Steps
    PostBuilding postBuilding;

    @Steps
    DeleteBuilding deleteBuilding;

    @Steps
    GetBuildingById getBuildingById;

    @Steps
    PostBuildingImage postBuildingImage;

    @Steps
    GetBuildings getBuildings;

    @Steps
    GetBuildingBySearch getBuildingBySearch;

    @Steps
    GetBuildingImage getBuildingImage;

    @Given("I set an endpoint for PUT edit building")
    public void iSetAnEndpointForPUTEditBuilding() {
        putBuilding.setPUTEndpointBuilding();
    }

    @When("I request PUT detail edit building with {string}, input {int}, {string}, {string}, {string}, {string}, and {int}")
    public void iRequestPUTDetailEditBuildingWithInputBuildingIdAndCapacity(String status, int buildingId, String name, String address, String desc, String buildSize, int capacity) throws IOException {
        putBuilding.requestPUTDetailBuilding(status, buildingId, name, address, desc, buildSize, capacity);
    }

    @Then("I validate the status code for put edit building is {int}")
    public void iValidateTheStatusCodeForPutEditBuildingIsStatusCode(int statusCode) {
        putBuilding.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after edit building")
    public void validateTheAndDataDetailsAfterEditBuilding(String message) {
        putBuilding.validateDataDetailAfterPUTBuilding(message);
    }

    @Given("I set an endpoint for POST building")
    public void iSetAnEndpointForPOSTBuilding() {
        postBuilding.setPostEndpointBuilding();
    }

    @When("I request POST detail building with {string}, input {int}, {string}, {string}, {string}, {string}, and {int}")
    public void iRequestPOSTDetailBuildingWithInputComplexIdAnd(String status, int complexId, String name, String address, String description, String building_size, int capacity) throws IOException {
        postBuilding.requestPostDetailBuilding(status, complexId, name, address, description, building_size, capacity);
    }

    @Then("I validate the status code for post building is {int}")
    public void iValidateTheStatusCodeForPostBuildingIsStatusCode(int statusCode) {
        postBuilding.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post building")
    public void validateTheAndDataDetailsAfterPostBuilding(String message) {
        postBuilding.validateDataDetailAfterPostBuilding(message);
    }

    @Given("I set an endpoint for DELETE building")
    public void iSetAnEndpointForDELETEBuilding() {
        deleteBuilding.setDeleteEndpointBuilding();
    }

    @When("I request DELETE detail building with {string} and input {int}")
    public void iRequestDELETEDetailBuildingWithAndInputBuildingId(String status, int buildingId) throws IOException {
        deleteBuilding.requestDeleteDetailBuilding(status, buildingId);
    }

    @Then("I validate the status code for delete building is {int}")
    public void iValidateTheStatusCodeForDeleteBuildingIsStatusCode(int statusCode) {
        deleteBuilding.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after delete building")
    public void validateTheAndDataDetailsAfterDeleteBuilding(String message) {
        deleteBuilding.validateDataDetailAfterDeleteBuilding(message);
    }

    @Given("I set an endpoint for GET building")
    public void iSetAnEndpointForGETBuilding() {
        getBuildingById.setGetEndpointBuilding();
    }

    @When("I request GET detail building with input {int}")
    public void iRequestGETDetailBuildingWithInputId(int id) {
        getBuildingById.requestGetDetailBuilding(id);
    }

    @Then("I validate the status code for get building is {int}")
    public void iValidateTheStatusCodeForGetBuildingIsStatusCode(int statusCode) {
        getBuildingById.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of building")
    public void validateTheAndDataDetailsOfBuilding(String message) {
        getBuildingById.validateTheDataDetailsOfBuilding(message);
    }

    @Given("I set an endpoint for POST building image")
    public void iSetAnEndpointForPOSTBuildingImage() {
        postBuildingImage.setPostEndpointBuildingImage();
    }

    @When("I request POST detail building image with {string}, input {int}, and {string}")
    public void iRequestPOSTDetailBuildingImageWithInputBuildingIdAnd(String status, int buildingId, String image) throws IOException {
        postBuildingImage.requestPostDetailBuildingImage(status, buildingId, image);
    }

    @Then("I validate the status code for post building image is {int}")
    public void iValidateTheStatusCodeForPostBuildingImageIsStatusCode(int statusCode) {
        postBuildingImage.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post building image")
    public void validateTheAndDataDetailsAfterPostBuildingImage(String message) {
        postBuildingImage.validateDataDetailAfterPostBuildingImage(message);
    }

    @Given("I set an endpoint for GET buildings")
    public void iSetAnEndpointForGETBuildings() {
        getBuildings.setGetEndpointBuilding();
    }

    @When("I request GET detail buildings with input {int}, {int}, and {int}")
    public void iRequestGETDetailBuildingsWithInput(int complexId, int page, int limit) {
        getBuildings.requestGetDetailBuilding(complexId, page, limit);
    }

    @Then("I validate the status code for get buildings is {int}")
    public void iValidateTheStatusCodeForGetBuildingsIsStatusCode(int statusCode) {
        getBuildings.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of buildings")
    public void validateTheAndDataDetailsOfBuildings(String message) {
        getBuildings.validateTheDataDetailsOfBuilding(message);
    }

    @Given("I set an endpoint for GET building by search")
    public void iSetAnEndpointForGETBuildingBySearch() {
        getBuildingBySearch.setGetEndpointBuilding();
    }

    @When("I request GET detail building with input {string}")
    public void iRequestGETDetailBuildingWithInput(String name) {
        getBuildingBySearch.requestGetDetailBuilding(name);
    }

    @Then("I validate the status code for get building by search is {}")
    public void iValidateTheStatusCodeForGetBuildingBySearchIs(int statusCode) {
        getBuildingBySearch.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of building by search")
    public void validateTheAndDataDetailsOfBuildingBySearch(String message) {
        getBuildingBySearch.validateTheDataDetailsOfBuilding(message);
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
