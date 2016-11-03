package email.pages;

import email.locators.MainLocators;
import email.utils.SettingsProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenSignIn extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    public OpenSignIn(final WebDriver driver) {
        super(driver);
    }

    public MainPage openSignInPage() {
        getDriver().get(SettingsProperties.getProperty("signIn_URL"));
        WebElement txtUsername = getDriver().findElement(By.id(MainLocators.USERNAME_TXT));
        waitForPageToLoadAndVerifyWe(txtUsername);
        return new MainPage(getDriver());
    }
}
