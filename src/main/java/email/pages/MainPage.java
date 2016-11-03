package email.pages;

import email.data.Users;
import email.locators.MainLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    @FindBy(id = MainLocators.USERNAME_TXT)
    private WebElement txtUsername;

    @FindBy(id = MainLocators.PASSWORD_TXT)
    private WebElement txtPassword;

    @FindBy(id = MainLocators.DOMAIN_DD)
    private WebElement ddDomain;

    @FindBy(id = MainLocators.SIGNIN_BTN)
    private WebElement btnSignIn;

    public MainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageToLoadAndVerifyWe(btnSignIn);
    }

    public LandingPage correctSignIn(final Users user) {
        log.info("User " + user.getUsername() + " tries to sign in " + user.getDomain());
        fillLoginForm(user);
        return new LandingPage(getDriver());
    }

    public AuthorizationPage wrongSignIn(final Users user) {
        log.info("User " + user.getUsername() + " tries to sign in " + user.getDomain());
        fillLoginForm(user);
        return new AuthorizationPage(getDriver());
    }

    private void fillLoginForm(final Users user) {
        txtUsername.clear();
        txtUsername.sendKeys(user.getUsername());
        txtPassword.clear();
        txtPassword.sendKeys(user.getPassword());
        // ddDomain.selectByValue(user.getDomain());
        btnSignIn.click();
    }
}
