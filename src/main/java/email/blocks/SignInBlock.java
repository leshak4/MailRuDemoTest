package email.blocks;

import email.data.Users;
import email.locators.CommonLocators;
import email.locators.SignInBlockLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("Sign In Block")
@Block(@FindBy(xpath = CommonLocators.COMMON_SIGNIN_BLOCK))
public class SignInBlock extends AbstractBlock {

    @FindBy(xpath = SignInBlockLocators.SIGNIN_BLOCK_USERNAME_INPUT)
    private TextInput signInBlockUsernameInput;

    @FindBy(xpath = SignInBlockLocators.SIGNIN_BLOCK_PASSWORD_INPUT)
    private TextInput signInBlockPasswordInput;

    @FindBy(xpath = SignInBlockLocators.SIGNIN_BLOCK_ENTER_BUTTON)
    private Button signInBlockEnterButton;

    @FindBy(xpath = SignInBlockLocators.SIGNIN_BLOCK_WRONG_CRED_MSG_STR)
    private WebElement signInBlockWrongCredMsgStr;

    @FindBy(xpath = SignInBlockLocators.SIGNIN_BLOCK_DROPDOWN_ARROW_WE)
    private WebElement signInBlockDDArrowWe;

    public void fillLoginForm(final Users user) {
        signInBlockUsernameInput.clear();
        signInBlockUsernameInput.sendKeys(user.getUsername());
        signInBlockPasswordInput.clear();
        signInBlockPasswordInput.sendKeys(user.getPassword());

        signInBlockDDArrowWe.click();

        final String domainXPath = SignInBlockLocators.SIGNIN_BLOCK_DROPDOWN_DOMAIN_LINK.replace("{domainName}", user.getDomain());
        WebElement domainItemLink = findByXpath(domainXPath);
        domainItemLink.click();

        signInBlockEnterButton.click();
    }

    public WebElement getWrongCredMsgWe() {
        return signInBlockWrongCredMsgStr;
    }

    public Boolean isWrongCredMsgDisplayed() {
        return isElementPresent(signInBlockWrongCredMsgStr);
    }

}
