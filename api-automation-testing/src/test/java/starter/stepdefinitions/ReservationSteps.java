package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.reservation.*;

import java.io.IOException;

public class ReservationSteps {

    @Steps
    PutCancel putCancel;

    @Steps
    GetReservationAdmin getReservationAdmin;

    @Steps
    PutReservation putReservation;

    @Steps
    PostReservationAdmin postReservationAdmin;

    @Steps
    GetReservationUser getReservationUser;

    @Steps
    PostReservationUser postReservationUser;

    @Steps
    PostPayment postPayment;

    @Steps
    GetPending getPending;

    @Steps
    GetPaymentImage getPaymentImage;

    @Given("I set an endpoint for PUT cancel reservation")
    public void iSetAnEndpointForPUTCancelReservation() {
        putCancel.setPUTEndpoint();
    }

    @When("I request PUT detail cancel reservation with {string} and input {string}")
    public void iRequestPUTDetailCancelReservationWithAndInput(String arg0, String arg2) throws IOException {
        putCancel.requestPUTDetail(arg0, arg2);
    }

    @Then("I validate the status code for cancel reservation is {int}")
    public void iValidateTheStatusCodeForCancelReservationIsStatusCode(int statusCode) {
        putCancel.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after cancel reservation")
    public void validateTheAndDataDetailsAfterCancelReservation(String arg0) {
        putCancel.validateDataDetail(arg0);
    }

    @Given("I set an endpoint for GET all reservation")
    public void iSetAnEndpointForGETAllReservation() {
        getReservationAdmin.setGetEndpoint();
    }

    @When("I request GET detail all reservation with {string} and {string}")
    public void iRequestGETDetailAllReservationWithAnd(String arg0, String arg1) throws IOException {
        getReservationAdmin.requestGetDetail(arg0, arg1);
    }

    @Then("I validate the status code for get all reservation is {int}")
    public void iValidateTheStatusCodeForGetAllReservationIsStatusCode(int statusCode) {
        getReservationAdmin.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of all reservation")
    public void validateTheAndDataDetailsOfAllReservation(String arg0) {
        getReservationAdmin.validateTheDataDetail(arg0);
    }

    @Given("I set an endpoint for PUT edit status reservation")
    public void iSetAnEndpointForPUTEditStatusReservation() {
        putReservation.setPUTEndpoint();
    }

    @When("I request PUT detail edit status reservation with {string}, {string}, input {string}, and {string}")
    public void iRequestPUTDetailEditStatusReservationWithInputAnd(String arg0, String arg1, String arg2, String arg3) throws IOException {
        putReservation.requestPUTDetail(arg0, arg1, arg2, arg3);
    }

    @Then("I validate the status code for PUT edit status reservation is {int}")
    public void iValidateTheStatusCodeForPUTEditStatusReservationIsStatusCode(int statusCode) {
        putReservation.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after edit status reservation")
    public void validateTheAndDataDetailsAfterEditStatusReservation(String arg0) {
        putReservation.validateDataDetail(arg0);
    }

    @Given("I set an endpoint for POST reservation admin")
    public void iSetAnEndpointForPOSTReservationAdmin() {
        postReservationAdmin.setPostEndpoint();
    }

    @When("I request POST detail reservation admin with {string}, input {string}, {int}, {string}, {string}, {int}, {string}, {int}, and {string}")
    public void iRequestPOSTDetailReservationAdminWithInputFloorIdPriceParticipantAnd(String arg0, String arg1, int floorId, String arg2, String arg3, int price, String arg4, int participant, String arg5) throws IOException {
        postReservationAdmin.requestPostDetail(arg0, arg1, floorId, arg2, arg3, price, arg4, participant, arg5);
    }

    @Then("I validate the status code for post reservation admin is {int}")
    public void iValidateTheStatusCodeForPostReservationAdminIsStatusCode(int statusCode) {
        postReservationAdmin.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post reservation admin")
    public void validateTheAndDataDetailsAfterPostReservationAdmin(String arg0) {
        postReservationAdmin.validateDataDetail(arg0);
    }

    @Given("I set an endpoint for GET reservation")
    public void iSetAnEndpointForGETReservation() {
        getReservationUser.setGetEndpoint();
    }

    @When("I request GET detail reservation with {string} and {string}")
    public void iRequestGETDetailReservationWithAnd(String arg0, String arg1) throws IOException {
        getReservationUser.requestGetDetail(arg0, arg1);
    }

    @Then("I validate the status code for get reservation is {int}")
    public void iValidateTheStatusCodeForGetReservationIsStatusCode(int statusCode) {
        getReservationUser.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of reservation")
    public void validateTheAndDataDetailsOfReservation(String arg0) {
        getReservationUser.validateTheDataDetail(arg0);
    }

    @Given("I set an endpoint for POST reservation user")
    public void iSetAnEndpointForPOSTReservationUser() {
        postReservationUser.setPostEndpoint();
    }

    @When("I request POST detail reservation user with {string}, input {int}, {string}, {string}, {string}, {int}, and {string}")
    public void iRequestPOSTDetailReservationUserWithInput(String arg0, int floorId, String arg1, String arg2, String arg3, int participant, String arg4) throws IOException {
        postReservationUser.requestPostDetail(arg0, floorId, arg1, arg2, arg3, participant, arg4);
    }

    @Then("I validate the status code for post reservation user is {int}")
    public void iValidateTheStatusCodeForPostReservationUserIsStatusCode(int statusCode) {
        postReservationUser.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post reservation user")
    public void validateTheAndDataDetailsAfterPostReservationUser(String arg0) {
        postReservationUser.validateDataDetail(arg0);
    }

    @Given("I set an endpoint for POST send payment reservation")
    public void iSetAnEndpointForPOSTSendPaymentReservation() {
        postPayment.setPostEndpoint();
    }

    @When("I request POST detail send payment reservation with {string}, input {string}, and {string}")
    public void iRequestPOSTDetailSendPaymentReservationWithInputAnd(String arg0, String arg1, String arg2) throws IOException {
        postPayment.requestPostDetail(arg0, arg1, arg2);
    }

    @Then("I validate the status code for post send payment reservation is {int}")
    public void iValidateTheStatusCodeForPostSendPaymentReservationIsStatusCode(int statusCode) {
        postPayment.validateStatusCode(statusCode);
    }

    @And("validate the {string} and data details after post send payment reservation")
    public void validateTheAndDataDetailsAfterPostSendPaymentReservation(String arg0) {
        postPayment.validateDataDetail(arg0);
    }

    @Given("I set an endpoint for GET pending reservation")
    public void iSetAnEndpointForGETPendingReservation() {
        getPending.setGetEndpoint();
    }

    @When("I request GET detail pending reservation with {string} and {string}")
    public void iRequestGETDetailPendingReservationWithAnd(String arg0, String arg1) throws IOException {
        getPending.requestGetDetail(arg0, arg1);
    }

    @Then("I validate the status code for get pending reservation is {int}")
    public void iValidateTheStatusCodeForGetPendingReservationIsStatusCode(int statusCode) {
        getPending.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data details of pending reservation")
    public void validateTheAndDataDetailsOfPendingReservation(String arg0) {
        getPending.validateTheDataDetail(arg0);
    }

    @Given("I set an endpoint for GET payment image")
    public void iSetAnEndpointForGETPaymentImage() {
        getPaymentImage.setGetEndpoint();
    }

    @When("I request GET detail payment image with {string} and input {string}")
    public void iRequestGETDetailPaymentImageWithAndInput(String arg0, String arg1) throws IOException {
        getPaymentImage.requestGetDetail(arg0, arg1);
    }

    @Then("I validate the status code for get payment image is {int}")
    public void iValidateTheStatusCodeForGetPaymentImageIsStatusCode(int statusCode) {
        getPaymentImage.validateTheStatusCode(statusCode);
    }

    @And("validate the {string} and data payment image")
    public void validateTheAndDataPaymentImage(String arg0) {
        getPaymentImage.validateTheDataDetail(arg0);
    }
}