package com.example.app.pages;

import com.example.app.base.BasePageObject;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class WelcomePage extends BasePageObject {

    By welcomeTitle() {
        return MobileBy.AccessibilityId("Welcome.");
    }

    By signInButton() {
        return MobileBy.AccessibilityId("Sign in");
    }

    By signUpButton() {
        return MobileBy.AccessibilityId("Sign up");
    }

    public void validateOnWelcomePage() {
        Assertions.assertTrue(isDisplayed(welcomeTitle()));
//        Assertions.assertEquals("Welcome.", getText(welcomeTitle()));
    }

    public void clickSignInButton() {
        click(signInButton());
    }

    public void clickSignUpButton() {
        click(signUpButton());
    }
}
