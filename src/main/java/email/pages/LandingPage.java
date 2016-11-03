package email.pages;

import email.locators.LandingLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractPage{

    @FindBy(id = LandingLocators.LOGGED_USER_EMAIL_STR)
    private WebElement strLoggedEmailWe;

    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageToLoadAndVerifyWe(strLoggedEmailWe);
    }

    public String getLoggedEmailText() {
        return strLoggedEmailWe.getText();
    }
}
