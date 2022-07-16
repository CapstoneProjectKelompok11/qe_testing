package com.example.app.pages;

import com.example.app.base.BasePageObject;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class HomePage extends BasePageObject {

    By homeMenu() {
        return MobileBy.AccessibilityId("Home Tab 1 of 4");
    }

    By topPlaces() {
        return MobileBy.AccessibilityId("Top Places");
    }

    By profileMenu() {
        return MobileBy.AccessibilityId("Profile Tab 4 of 4");
    }

    public void validateOnHomePage() {
        Assertions.assertTrue(isDisplayed(topPlaces()));
    }

    public void clickProfileMenu() {
        click(profileMenu());
    }
}
