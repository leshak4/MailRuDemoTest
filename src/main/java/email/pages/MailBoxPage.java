package email.pages;

import email.blocks.MailHomePaneBlock;
import email.data.EmailDetails;
import email.data.Users;
import email.locators.MailBoxLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;

public class MailBoxPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    private MailHomePaneBlock mailHomePaneBlock;

    @FindBy(id = MailBoxLocators.MAILBOX_LOGGED_USER_EMAIL_LINK)
    private Link mailboxLoggedEmailLink;

    public MailBoxPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.id(MailBoxLocators.MAILBOX_LOGGED_USER_EMAIL_LINK));
    }

    public String getLoggedEmailText() {
        return mailboxLoggedEmailLink.getText();
    }

    public CreateEmailFormPage openCreateEmailForm() {
        mailHomePaneBlock.openCreateEmailForm();
        return new CreateEmailFormPage(getDriver());
    }

    public WebElement getEmailDisplayedInList(Users userTo, EmailDetails emailDetails) {
        String emailTo = userTo.getUsername() + userTo.getDomain();
        String emailSubj = emailDetails.getSubject();
        String emailItemXpath = MailBoxLocators.MAILBOX_EMAIL_ITEM_LINK.replace("{email}", emailTo).replace("{subject}", emailSubj);
        WebElement emailItemLink = findByXpath(emailItemXpath);
        return emailItemLink;
    }

    public boolean isEmailDisplayedInList(Users userTo, EmailDetails emailDetails) {
        return getEmailDisplayedInList(userTo, emailDetails).isDisplayed();
    }

    public SentEmailFormPage openSentEmail(Users userTo, EmailDetails emailDetails) {
        getEmailDisplayedInList(userTo, emailDetails).click();
        return new SentEmailFormPage(getDriver());
    }

    public CreateEmailFormPage openDraftEmail(Users userTo, EmailDetails emailDetails) {
        getEmailDisplayedInList(userTo, emailDetails).click();
        return new CreateEmailFormPage(getDriver());
    }

    public MailBoxPage openSent() {
        mailHomePaneBlock.openSent();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage openDrafts() {
        mailHomePaneBlock.openDrafts();
        return new MailBoxPage(getDriver());
    }


}
