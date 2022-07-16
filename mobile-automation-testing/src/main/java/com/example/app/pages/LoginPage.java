package com.example.app.pages;

import com.example.app.base.BasePageObject;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class LoginPage extends BasePageObject {

    By signInTitle() {
        return MobileBy.AccessibilityId("Sign in to continue");
    }

    By signInButton() {
        return MobileBy.AccessibilityId("Sign in");
    }

    By emailField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    }

    By emailField2() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]");
    }

    By passwordField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    }

    By passwordField2() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]");
    }

    By cart() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View");
    }

    By continueButton() {
        return MobileBy.AccessibilityId("Continue");
    }

    By tryAgainButton() {
        return MobileBy.AccessibilityId("Try Again");
    }

    By requiredMessage(int field) {
        return By.xpath("(//android.view.View[@content-desc=\"This field cannot be empty\"])[" + field + "]");
    }

    public void validateOnSignInPage() {
        Assertions.assertTrue(isDisplayed(signInTitle()));
//        Assertions.assertEquals("Sign in to continue", getText(signInTitle()));
    }

    public void inputEmail(String email) {
        click(emailField());
        sendKeys(emailField2(), email);
    }

    public void inputPassword(String password) {
        click(passwordField2());
        sendKeys(passwordField2(), password);
    }

    public void clickSignInButton() {
        swipeVertical();
        click(signInButton());
    }

    public void validateCartIsDisplayed() {
        Assertions.assertTrue(isDisplayed(cart()));
    }

    public void clickContinueButton() {
        click(continueButton());
    }

    public void clickTryAgainButton() {
        click(tryAgainButton());
    }

    public void verifyRequiredMessage(String message) {
        if (message.equals("required email")) {
            Assertions.assertTrue(isDisplayed(requiredMessage(1)));
        } else {
            Assertions.assertTrue(isDisplayed(requiredMessage(2)));
        }
    }
}