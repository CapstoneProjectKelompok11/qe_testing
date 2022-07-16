package com.example.app.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static com.example.app.drivers.AndroidDriverInit.driver;

public class BasePageObject {

    public AndroidDriver<AndroidElement> getDriver(){
        return driver;
    }

    public WebDriverWait onWait(){
        return new WebDriverWait(getDriver(), 30, 1000);
    }

    public AndroidElement find(By locator){
        return (AndroidElement) onWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator){
        find(locator).click();
    }

    public void sendKeys(By locator, String input){
        find(locator).sendKeys(input);
    }

    public void clear(By locator){
        find(locator).clear();
    }

    public boolean isDisplayed(By locator){
        return find(locator).isDisplayed();
    }

    public String getText(By locator){
        return find(locator).getText();
    }

    public void swipeHorizontal(){
        TouchAction action = new TouchAction(driver);
        Dimension sizeDevice = driver.manage().window().getSize();
        action.press(PointOption.point((int) (sizeDevice.getWidth() * 0.8), sizeDevice.getHeight()))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point((int) (sizeDevice.getWidth()* 0.2), sizeDevice.getHeight()))
                .release().perform();
    }

    public void swipeVertical(){
        TouchAction action = new TouchAction(driver);
        Dimension sizeDevice = driver.manage().window().getSize();

        action.press(PointOption.point((int) (sizeDevice.getWidth() * 0.5), (int) (sizeDevice.getHeight() * 0.4)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point((int) (sizeDevice.getWidth() * 0.5), (int) (sizeDevice.getHeight() * 0.3)))
                .release().perform();
    }

    public void scroll(By element) {
        TouchActions action = new TouchActions(driver);
        action.scroll(find(element), 10, 100);
        action.perform();
    }

    public String randomEmail() {
        Random rand = new Random();
        return "usermail" + rand.nextInt(1000) + "@gmail.com";
    }
}
