package com.example.app.pages;

import com.example.app.base.BasePageObject;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class ProfilePage extends BasePageObject {

    By profileTitle() {
        return MobileBy.AccessibilityId("Profile");
    }

    By signOutButton() {
        return MobileBy.AccessibilityId("Sign Out");
    }

    public void validateOnProfilePage() {
        Assertions.assertTrue(isDisplayed(profileTitle()));
//        Assertions.assertEquals("Profile", getText(profileTitle()));
    }

    public void clickSignOutButton() {
        click(signOutButton());
    }
}
