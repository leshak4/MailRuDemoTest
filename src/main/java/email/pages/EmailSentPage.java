package email.pages;

import email.blocks.MailFoldersBlock;
import email.locators.EmailSentLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailSentPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    private MailFoldersBlock mailFoldersBlock;

    @FindBy(xpath = EmailSentLocators.EMAILSENT_CONFIRMATION)
    private WebElement emailSentConfirmation;

    @FindBy(xpath = EmailSentLocators.EMAILSENT_EMAIL_TO)
    private WebElement emailSentAddressTo;

    public EmailSentPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(EmailSentLocators.EMAILSENT_EMAIL_TO));
    }

    public boolean isEmailSentConfirmationDisplayed() {
        return emailSentConfirmation.isDisplayed();
    }

    public String getDisplayedEmailTo() {
        return emailSentAddressTo.getText();
    }

    public MailBoxPage openSent() {
        log.info("sent folder opening");
        waitUntilDisplayed(mailFoldersBlock, 5);
        mailFoldersBlock.openSent();
        return new MailBoxPage(getDriver());
    }


}
