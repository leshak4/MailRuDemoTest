package email.pages;

import email.blocks.SignInBlock;
import email.data.Users;
import email.locators.SignInLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class SignInPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    private SignInBlock signInBlock;

    @FindBy(xpath = SignInLocators.SIGNIN_WRONG_CRED_MSG_STR)
    private WebElement signInWrongCredMsgStr;

    @FindBy(xpath = SignInLocators.SIGNIN_USERNAME_INPUT)
    private TextInput signInUsernameInput;

    @FindBy(xpath = SignInLocators.SIGNIN_PASSWORD_INPUT)
    private TextInput signInPasswordInput;

    @FindBy(xpath = SignInLocators.SIGNIN_ENTER_BUTTON)
    private Button signInEnterButton;

    @FindBy(xpath = SignInLocators.SIGNIN_CREDS_FRAME)
    private WebElement signInCredsFrame;

    public SignInPage(final WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(SignInLocators.SIGNIN_CREDS_FRAME));
        switchToFrame(signInCredsFrame);
        waitForPageToLoadAndVerifyBy(By.xpath(SignInLocators.SIGNIN_USERNAME_INPUT));
        waitForPageToLoadAndVerifyBy(By.xpath(SignInLocators.SIGNIN_PASSWORD_INPUT));
        sleep(300);
        switchToDefaultContent();
    }

    public MailBoxPage signIn(final Users user) {
        log.info("User " + user.getUsername() + " tries to sign in to " + user.getDomain());
        switchToFrame(signInCredsFrame);
        signInBlock.fillLoginForm(user);
        return new MailBoxPage(getDriver());
    }

    public SignInSimplePage wrongSignIn(final Users user) {
        log.info("User " + user.getUsername() + " tries to sign in to " + user.getDomain());
        switchToFrame(signInCredsFrame);
        signInBlock.fillLoginForm(user);
        return new SignInSimplePage(getDriver());
    }

}
