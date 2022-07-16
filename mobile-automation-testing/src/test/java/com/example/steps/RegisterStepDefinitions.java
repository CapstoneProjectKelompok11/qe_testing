package com.example.steps;

import com.example.app.pages.HomePage;
import com.example.app.pages.LoginPage;
import com.example.app.pages.RegisterPage;
import com.example.app.pages.WelcomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterStepDefinitions {

    WelcomePage welcomePage = new WelcomePage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @When("I click sign up button")
    public void iClickSignUpButton() {
        welcomePage.clickSignUpButton();
    }

    @Given("I am on sign up page")
    public void iAmOnSignUpPage() {
        registerPage.validateOnSignUpPage();
    }

    @When("I input {string}, {string}, {string}, {string} and {string}")
    public void iInputAnd(String firstname, String lastname, String phone, String email, String password) throws InterruptedException {
        registerPage.inputFirstname(firstname);
        registerPage.inputLastname(lastname);
        registerPage.inputPhone(phone);
        registerPage.inputEmail(email);
        registerPage.inputPassword(password);
    }

    @And("click sign up button")
    public void clickSignUpButton() {
        registerPage.clickSignUpButton();
    }

    @Then("validate the {string} after sign up")
    public void validateTheAfterSignUp(String message) {
        if (message.equals("success")) {
            registerPage.verifySuccessSignUp();
            registerPage.clickContinueButton();
            loginPage.validateOnSignInPage();
        } else {
            registerPage.verifyRequiredMessage(message);
        }
    }
}
