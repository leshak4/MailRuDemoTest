package email.pages;

import email.locators.LandingLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;

public class LandingPage extends AbstractPage {

    @FindBy(id = LandingLocators.LAND_LOGGED_USER_EMAIL_LINK)
    private Link landLoggedEmailLink;

    public LandingPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.id(LandingLocators.LAND_LOGGED_USER_EMAIL_LINK));
    }

    public String getLoggedEmailText() {
        return landLoggedEmailLink.getText();
    }
}
