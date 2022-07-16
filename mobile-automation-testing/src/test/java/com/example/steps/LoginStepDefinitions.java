package com.example.steps;

import com.example.app.pages.HomePage;
import com.example.app.pages.LoginPage;
import com.example.app.pages.WelcomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    WelcomePage welcomePage = new WelcomePage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("I am on welcome page")
    public void iAmOnWelcomePage() {
        welcomePage.validateOnWelcomePage();
    }

    @When("I click sign in button")
    public void iClickSignInButton() {
        welcomePage.clickSignInButton();
    }

    @Given("I am on login page")
    public void iAmOnLoginPage() {
        loginPage.validateOnSignInPage();
    }

    @When("I input {string} and {string}")
    public void iInputAnd(String email, String password) {
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
    }

    @And("click login button")
    public void clickLoginButton() {
        loginPage.clickSignInButton();
    }

    @Then("validate the {string} after login")
    public void validateTheAfterLogin(String message) {
        if (message.equals("success")) {
            loginPage.validateCartIsDisplayed();
            loginPage.clickContinueButton();
            homePage.validateOnHomePage();
        } else if (message.equals("not existed")) {
            loginPage.validateCartIsDisplayed();
            loginPage.clickTryAgainButton();
        } else {
            loginPage.verifyRequiredMessage(message);
        }
    }
}
