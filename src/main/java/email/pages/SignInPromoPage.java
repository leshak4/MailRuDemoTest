package email.pages;

import email.blocks.SignInBlock;
import email.data.Users;
import email.locators.SignInPromoLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class SignInPromoPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    private SignInBlock signInBlock;

    @FindBy(xpath = SignInPromoLocators.SIGNINPROMO_WRONG_CRED_MSG_STR)
    private WebElement signInPromoWrongCredMsgStr;

    @FindBy(xpath = SignInPromoLocators.SIGNINPROMO_USERNAME_INPUT)
    private TextInput signInPromoUsernameInput;

    @FindBy(xpath = SignInPromoLocators.SIGNINPROMO_PASSWORD_INPUT)
    private TextInput signInPromoPasswordInput;

    @FindBy(xpath = SignInPromoLocators.SIGNINPROMO_ENTER_BUTTON)
    private Button signInPromoEnterButton;

    @FindBy(xpath = SignInPromoLocators.SIGNINPROMO_CREDS_FRAME)
    private WebElement signInPromoCredsFrame;

    public SignInPromoPage(final WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(SignInPromoLocators.SIGNINPROMO_CREDS_FRAME));
    }

    public LandingPage correctSignInPromo(final Users user) {
        log.info("User " + user.getUsername() + " tries to sign in to " + user.getDomain());
        switchToFrame(signInPromoCredsFrame);
        signInBlock.fillLoginForm(user);
        return new LandingPage(getDriver());
    }

    public SignInSimplePage wrongSignInPromo(final Users user) {
        log.info("User " + user.getUsername() + " tries to sign in to " + user.getDomain());
        switchToFrame(signInPromoCredsFrame);
        signInBlock.fillLoginForm(user);
        return new SignInSimplePage(getDriver());
    }

}
