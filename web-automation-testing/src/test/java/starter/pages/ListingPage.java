package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class ListingPage extends PageObject {

    private By listingHeader() {
        return By.xpath("//*[@class='mx-4'][text()='Rent an Office']");
    }

    private By userIcon() {
        return By.xpath("//*[@href='/profile']");
    }

    private By buyButton(String id_product) {
        return By.xpath("//*[@class='col-md-4 col-lg-3 col-6'][" + id_product + "]//span[text()='Beli']");
    }

    private By cartButton() {
        return By.xpath("//*[@id=\"app\"]/div/header/div/button[1]");
    }

    private By detailButton(String id_product) {
        return By.xpath("//*[@class='col-md-4 col-lg-3 col-6'][" + id_product + "]//span[text()='Detail']");
    }

    private By logoutButton() {
        return By.xpath("//*[@class=\"v-list v-sheet theme--light\"]/div[2]");
    }

    @Step
    public void openUrl() {
        open();
    }

    @Step
    public boolean headerAppears() {
        return $(listingHeader()).isDisplayed();
    }

    @Step
    public boolean headerTextEqual() {
        return $(listingHeader()).getText().equals("Rent an Office");
    }

    @Step
    public boolean validateOnListingPage() {
        return $(userIcon()).isDisplayed();
    }

    @Step
    public void clickUserIcon() {
        $(userIcon()).click();
    }

    @Step
    public void clickBuyButton(String id) {
        $(buyButton(id)).click();
    }

    @Step
    public void clickCartButton() {
        $(cartButton()).click();
    }

    @Step
    public void clickDetailButton(String id_product) {
        $(detailButton(id_product)).click();
    }

    @Step
    public void clickLogoutButton() {
        $(logoutButton()).click();
    }
}
