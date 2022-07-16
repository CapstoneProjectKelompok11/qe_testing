package com.example.steps;

import com.example.app.pages.HomePage;
import com.example.app.pages.LoginPage;
import com.example.app.pages.ProfilePage;
import com.example.app.pages.WelcomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutStepDefinitions {

    WelcomePage welcomePage = new WelcomePage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ProfilePage profilePage = new ProfilePage();

    @Given("I have logged in as user")
    public void iHaveLoggedInAsUser() throws InterruptedException {
        welcomePage.clickSignInButton();
        loginPage.inputEmail("string");
        loginPage.inputPassword("string");
        loginPage.clickSignInButton();
        loginPage.clickContinueButton();
    }

    @Given("I am on home page")
    public void iAmOnHomePage() {
        homePage.validateOnHomePage();
    }

    @When("I click profile menu")
    public void iClickProfileMenu() {
        homePage.clickProfileMenu();
        profilePage.validateOnProfilePage();
    }

    @And("click sign out button")
    public void clickSignOutButton() {
        profilePage.clickSignOutButton();
    }

    @Then("validate success sign out")
    public void validateSuccessSignOut() {
        loginPage.validateOnSignInPage();
    }
}
