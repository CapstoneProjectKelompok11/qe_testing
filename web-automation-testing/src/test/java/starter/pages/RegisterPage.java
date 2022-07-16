package starter.pages;

import Utils.General;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterPage extends PageObject {

    General general = new General();
    private String email;

    private By registerHeader() {
        return By.xpath("//*[p[text()='Sign up your account']]");
    }

    private By firstNameField() {
        return By.xpath("//*[label[text()='First Name']]/input");
    }

    private By lastNameField() {
        return By.xpath("//*[label[text()='Last Name']]/input");
    }

    private By phoneField() {
        return By.xpath("//*[label[text()='Phone Number']]/input");
    }

    private By emailField() {
        return By.xpath("//*[label[text()='Email']]/input");
    }

    private By passwordField() {
        return By.xpath("//*[label[text()='Password']]/input");
    }

    private By confirmPasswordField() {
        return By.xpath("//*[label[text()='Confirmation Password']]/input");
    }

    private By registerButton() {
        return By.xpath("//*[@type='submit']");
    }

//    private By iconWarning() {
//        return By.xpath("//*[@class=\"v-alert__wrapper\"]");
//    }

    private By textWarning() {
        return By.xpath("//*[label[text()='Confirmation Password']]/p[text()]");
    }

    @Step
    public void openUrl() {
        openAt("/Register");
    }

    @Step
    public boolean headerAppears() {
        return $(registerHeader()).isDisplayed();
    }

    @Step
    public boolean headerTextEqual() {
        return $(registerHeader()).getText().equals("Register");
    }

    @Step
    public void inputFirstName(String firstName) {
        $(firstNameField()).type(firstName);
    }

    @Step
    public void inputLastName(String lastName) {
        $(lastNameField()).type(lastName);
    }

    @Step
    public void inputPhone(String phone) {
        $(phoneField()).type(phone);
    }

    @Step
    public void inputEmail(String email) {
        if (email.equals("new")) {
            this.email = general.randomEmail();
            try (FileWriter file = new FileWriter("src/test/resources/filejson/email.json")) {
                file.write(this.email);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (email.equals("same")) {
            this.email = "user@gmail.com";
        } else {
            this.email = email;
        }
        $(emailField()).type(this.email);
    }

    @Step
    public void inputPassword(String password) {
        $(passwordField()).type(password);
    }

    @Step
    public void clickRegisterButton() {
        $(registerButton()).click();
    }

//    @Step
//    public boolean iconWarningAppears(){
//        return $(iconWarning()).isDisplayed();
//    }

    @Step
    public boolean textWarningEquals(String text) {
        return $(textWarning()).getText().equals(text);
    }
}
