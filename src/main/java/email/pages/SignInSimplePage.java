package email.pages;

import email.blocks.SignInBlock;
import email.locators.SignInSimpleLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInSimplePage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    private SignInBlock signInBlock;

    public SignInSimplePage(final WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(SignInSimpleLocators.SIGNINSIMPLE_USERNAME_INPUT));
    }

    public Boolean isWrongCredMsgDisplayed() {
        return signInBlock.isWrongCredMsgDisplayed();
    }


}
