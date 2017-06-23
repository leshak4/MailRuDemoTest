package email.pages;

import email.locators.MainLocators;
import email.utils.SettingsProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenMainPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    public OpenMainPage(final WebDriver driver) {
        super(driver);
    }

    public MainPage openMainPage() {
        getDriver().get(SettingsProperties.getProperty("signIn_URL"));
        WebElement txtUsername = getDriver().findElement(By.xpath(MainLocators.MAIN_SEARCH_LINE_INPUT));
        waitForPageToLoadAndVerifyWe(txtUsername);
        return new MainPage(getDriver());
    }
}
