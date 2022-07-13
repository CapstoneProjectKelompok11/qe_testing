package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.favorite.DeleteFav;
import starter.favorite.GetFav;
import starter.favorite.PostFav;

import java.io.IOException;

public class FavSteps {

    @Steps
    PostFav postFav;

    @Steps
    GetFav getFav;

    @Steps
    DeleteFav deleteFav;

    @Given("I set an endpoint for POST favorite")
    public void iSetAnEndpointForPOSTFavorite() {
        postFav.setPostEndpointFavorite();
    }

    @When("I request POST detail favorite with {string} and input {int}")
    public void iRequestPOSTDetailFavoriteWithAndInputBuildingId(String status, int buildingId) throws IOException {
        postFav.requestPostDetailFavorite(status, buildingId);
    }

    @Then("I validate the status code for post favorite is {int}")
    public void iValidateTheStatusCodeForPostFavoriteIsStatusCode(int statusCode) {
        postFav.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post favorite")
    public void validateTheAndDataDetailsAfterPostFavorite(String message) {
        postFav.validateDataDetailAfterPostFavorite(message);
    }

    @Given("I set an endpoint for GET favorite building")
    public void iSetAnEndpointForGETFavoriteBuilding() {
        getFav.setGetEndpointFavorite();
    }

    @When("I request GET detail favorite building with {string} and {string}")
    public void iRequestGETDetailFavoriteBuildingWithAnd(String status, String token) throws IOException {
        getFav.requestGetDetailFavorite(status, token);
    }

    @Then("I validate the status code for get favorite building is {int}")
    public void iValidateTheStatusCodeForGetFavoriteBuildingIs(int statusCode) {
        getFav.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of favorite building")
    public void validateTheAndDataDetailsOfFavoriteBuilding(String message) {
        getFav.validateTheDataDetailsOfFavorite(message);
    }

    @Given("I set an endpoint for DELETE favorite")
    public void iSetAnEndpointForDELETEFavorite() {
        deleteFav.setDeleteEndpointFavorite();
    }

    @When("I request DELETE detail favorite with {string} and input {int}")
    public void iRequestDELETEDetailFavoriteWithAndInputBuildingId(String status, int buildingId) throws IOException {
        deleteFav.requestDeleteDetailFavorite(status, buildingId);
    }

    @Then("I validate the status code for delete favorite is {int}")
    public void iValidateTheStatusCodeForDeleteFavoriteIsStatusCode(int statusCode) {
        deleteFav.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after delete favorite")
    public void validateTheAndDataDetailsAfterDeleteFavorite(String message) {
        deleteFav.validateDataDetailAfterDeleteFavorite(message);
    }
}
