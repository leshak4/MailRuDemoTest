package email.pages;

import email.locators.AuthorizationLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage extends AbstractPage {

    @FindBy(xpath = AuthorizationLocators.AUTH_FORM_HEADER_STR)
    private WebElement strAuthFormHeader;

    @FindBy(xpath = AuthorizationLocators.AUTH_FORM_WRONG_CRED_MSG_STR)
    private WebElement strAuthFormWrongCredMsg;

    public AuthorizationPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageToLoadAndVerifyWe(strAuthFormHeader);
    }

    public WebElement getWrongCredMsgWe() {
        return strAuthFormWrongCredMsg;
    }

    public Boolean isWrongCredMsgDisplayed() {
        return isElementPresent(getWrongCredMsgWe());
    }
}
