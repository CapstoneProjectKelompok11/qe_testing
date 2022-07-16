package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.ListingPage;
import starter.pages.LoginPage;

public class LoginSteps {

    @Steps
    ListingPage listingPage;

    @Steps
    LoginPage loginPage;

//    @Given("I am on product page")
//    public void iAmOnProductPage() {
//        productPage.open();
//        productPage.headerAppears();
//        productPage.headerTextEqual();
//        productPage.validateOnHomePage();
//    }

//    @And("I click user icon")
//    public void iClickUserIcon() {
//        productPage.clickUserIcon();
//    }

    @Given("I am on login page")
    public void iAmOnLoginPage() {
        loginPage.openUrl();
        loginPage.headerAppears();
        loginPage.headerTextEqual();
        loginPage.validateOnLoginPage();
    }

    @When("I input {string} email")
    public void iInputEmail(String email) {
        loginPage.inputEmail(email);
    }

    @And("input {string} password on login page")
    public void inputPasswordOnLoginPage(String password) {
        loginPage.inputPassword(password);
    }

    @And("click login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I get the {string} and {string} after login")
    public void iGetThe(String result, String text) {
        if(result.equals("icon warning")){
//            loginPage.iconWarningAppears();
//            loginPage.textWarningEquals(text);
        } else if (result.equals("direct to homepage")) {
            listingPage.headerAppears();
            listingPage.headerTextEqual();
        } else {
//            loginPage.iconWarningAppears();
        }
    }
}