package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    private By loginHeader() {
        return By.xpath("//*[p[text()='Login']]");
    }

    private By linkSignUp() {
        return By.xpath("//*[@href='/Register']");
    }

    private By rentOfficeMenu() {
        return By.xpath("//*[@href='/listing']");
    }

    private By emailField() {
        return By.xpath("//*[label[text()='Email']]/input");
    }

    private By passwordField() {
        return By.xpath("//*[label[text()='Password']]/input");
    }

    private By loginButton() {
        return By.xpath("//*[@type='submit']");
    }

//    private By iconWarning() {
//        return By.xpath("//*[@class=\"v-alert__wrapper\"]");
//    }

//    private By textWarning() {
//        return By.xpath("//*[@class=\"v-alert__content\"]");
//    }

    @Step
    public void openUrl() {
//        openUrl();
        open();
    }

    @Step
    public boolean headerAppears() {
        return $(loginHeader()).isDisplayed();
    }

    @Step
    public boolean headerTextEqual() {
        return $(loginHeader()).getText().equals("Login");
    }

    @Step
    public boolean validateOnLoginPage() {
        return $(linkSignUp()).isDisplayed();
    }

    @Step
    public void clickLinkRegister() {
        $(linkSignUp()).click();
    }

    @Step
    public void inputEmail(String email) {
        $(emailField()).type(email);
    }

    @Step
    public void inputPassword(String password) {
        $(passwordField()).type(password);
    }

    @Step
    public void clickLoginButton() {
        $(loginButton()).click();
    }

    @Step
    public void clickRentOfficeMenu() {
        $(rentOfficeMenu()).click();
    }

//    @Step
//    public boolean iconWarningAppears(){
//        return $(iconWarning()).isDisplayed();
//    }

//    @Step
//    public boolean textWarningEquals(String text) {
//        return $(textWarning()).getText().equals(text);
//    }
}
