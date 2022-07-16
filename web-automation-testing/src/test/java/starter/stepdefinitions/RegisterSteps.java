package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.ListingPage;
import starter.pages.LoginPage;
import starter.pages.RegisterPage;

public class RegisterSteps {

    @Steps
    ListingPage listingPage;

    @Steps
    LoginPage loginPage;

    @Steps
    RegisterPage registerPage;

    @Given("I am on register page")
    public void iAmOnRegisterPage() {
        registerPage.openUrl();
        registerPage.headerAppears();
        registerPage.headerTextEqual();
    }

    @When("I input {string} firstName")
    public void iInputFirstName(String firstname) {
        registerPage.inputFirstName(firstname);
    }

    @And("input {string} lastName")
    public void iInputLastName(String lastname) {
        registerPage.inputLastName(lastname);
    }

    @And("input {string} phone")
    public void inputPhone(String phone) {
        registerPage.inputPhone(phone);
    }

    @And("input {string} email")
    public void inputEmail(String email) {
        registerPage.inputEmail(email);
    }

    @And("input {string} password")
    public void inputPassword(String password) {
        registerPage.inputPassword(password);
    }

    @And("click register button")
    public void clickRegisterButton() {
        registerPage.clickRegisterButton();
    }

    @And("double click register button")
    public void doubleClickRegisterButton() {
        registerPage.clickRegisterButton();
        registerPage.clickRegisterButton();
    }

    @Then("I get the {string} and {string} after register")
    public void iGetTheAndAfterRegister(String result, String text) {
        if(result.equals("icon warning")){
//            registerPage.iconWarningAppears();
//            registerPage.textWarningEquals(text);
        } else if (result.equals("direct to login page")) {
//            loginPage.headerAppears();
//            loginPage.headerTextEqual();
        } else {
//            registerPage.iconWarningAppears();
        }
    }
}