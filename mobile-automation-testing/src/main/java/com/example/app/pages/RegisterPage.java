package com.example.app.pages;

import com.example.app.base.BasePageObject;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterPage extends BasePageObject {

    String email;

    By signUpTitle() {
        return MobileBy.AccessibilityId("Getting started");
    }

    By signUpButton() {
        return MobileBy.AccessibilityId("Sign up");
    }

    By firstnameField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]");
    }

    By lastnameField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]");
    }

    By phoneField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[3]");
    }

    By emailField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[4]");
    }

    By passwordField() {
        return By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[4]");
    }

    By successVerify() {
        return MobileBy.AccessibilityId("Verification Successful!");
    }

    By continueButton() {
        return MobileBy.AccessibilityId("Continue");
    }

    By requiredMessage(String fields, int field) {
        if (fields.equals("email")) {
            return MobileBy.AccessibilityId("Enter valid email!");
        } else if (fields.equals("password")) {
            return MobileBy.AccessibilityId("Enter at least 8 characters");
        } else {
            return By.xpath("(//android.view.View[@content-desc=\"This field cannot be empty\"])[" + field + "]");
        }
    }

    public void validateOnSignUpPage() {
        Assertions.assertTrue(isDisplayed(signUpTitle()));
    }

    public void inputFirstname(String firstname) throws InterruptedException {
        Thread.sleep(5000);
        click(firstnameField());
        Thread.sleep(3000);
        sendKeys(firstnameField(), firstname);
    }

    public void inputLastname(String lastname) {
        click((lastnameField()));
        sendKeys(lastnameField(), lastname);
    }

    public void inputPhone(String phone) {
        swipeVertical();
        click(phoneField());
        sendKeys(phoneField(), phone);
    }

    public void inputEmail(String email) {
        if (email.equals("new")) {
            this.email = randomEmail();
            try (FileWriter file = new FileWriter("src//test//resources//file//email.json")){
                file.write(this.email);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (email.equals("existed")) {
            this.email = "usermail@gmail.com";
        } else if (email.equals("invalid format")){
            this.email = "string";
        } else {
            this.email = email;
        }

        swipeVertical();
        click(emailField());
        sendKeys(emailField(), this.email);
    }

    public void inputPassword(String password) {
        swipeVertical();
        click(passwordField());
        sendKeys(passwordField(), password);
    }

    public void clickSignUpButton() {
        swipeVertical();
        click(signUpButton());
    }

    public void verifySuccessSignUp() {
        Assertions.assertTrue(isDisplayed(successVerify()));
    }

    public void clickContinueButton() {
        click(continueButton());
    }

    public void verifyRequiredMessage(String message) {
        if (message.equals("required email") || message.equals("invalid email")) {
            Assertions.assertTrue(isDisplayed(requiredMessage("email", 0)));
        } else if (message.equals("required password") || message.equals("invalid password")){
            Assertions.assertTrue(isDisplayed(requiredMessage("password", 0)));
        } else if (message.equals("required firstname")) {
            Assertions.assertTrue(isDisplayed(requiredMessage("firstname", 1)));
        } else if (message.equals("required lastname")) {
            Assertions.assertTrue(isDisplayed(requiredMessage("lastname", 2)));
        } else {
            Assertions.assertTrue(isDisplayed(requiredMessage("phone", 3)));
        }
    }
}