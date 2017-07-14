package email.pages;

import email.locators.MailBoxLocators;
import email.locators.MainLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class MainPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    @FindBy(xpath = MainLocators.MAIN_BREADCRUMBS_POST_LINK)
    private Link mainBreadCrumbPostLink;

    @FindBy(xpath = MainLocators.MAIN_SEARCH_LINE_INPUT)
    private TextInput mainSearchLineInput;

    @FindBy(xpath = MainLocators.MAIN_REGISTRATION_LINK)
    private Link mainRegistrationLink;

    @FindBy(id = MailBoxLocators.MAILBOX_LOGGED_USER_EMAIL_LINK)
    private WebElement mailboxLoggedEmailLink;

    public MainPage(final WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(MainLocators.MAIN_SEARCH_LINE_INPUT));
    }

    public SignInPage openSignInPage() {
        log.info("log in form opening");
        mainBreadCrumbPostLink.click();
        return new SignInPage(getDriver());
    }

    public RegPage openRegPage() {
        log.info("registration form opening");
        mainRegistrationLink.click();
        return new RegPage(getDriver());
    }

    public Boolean isLoggedUserEmailDisplayed() {
        return isElementPresent(mailboxLoggedEmailLink, "Email of a logged user", 3);
    }

}
