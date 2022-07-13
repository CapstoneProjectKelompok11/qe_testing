package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.chat.GetChat;

import java.io.IOException;

public class ChatSteps {

    @Steps
    GetChat getChat;

    @Given("I set an endpoint for GET chat")
    public void iSetAnEndpointForGETChat() {
        getChat.setGetEndpointChat();
    }

    @When("I request GET detail chat")
    public void iRequestGETDetailChat() throws IOException {
        getChat.requestGetDetailChat();
    }

    @Then("I validate the status code for get list chat is {int}")
    public void iValidateTheStatusCodeForGetListChatIs(int statusCode) {
        getChat.validateTheStatusCode(statusCode);
    }

    @And("validate the data details of chat")
    public void validateTheDataDetailsOfChat() {
        getChat.validateTheDataDetailsOfChat();
    }
}
