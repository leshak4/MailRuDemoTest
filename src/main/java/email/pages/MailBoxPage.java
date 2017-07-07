package email.pages;

import email.blocks.FooterBlock;
import email.blocks.LanguageSwitcherBlock;
import email.blocks.MailFoldersBlock;
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

    private FooterBlock footerBlock;

    private LanguageSwitcherBlock languageSwitcherBlock;

    private MailFoldersBlock mailFoldersBlock;

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

    public WebElement getEmailDisplayedInList(EmailDetails emailDetails) {
        String emailItemXpath = MailBoxLocators.MAILBOX_EMAIL_ITEM_LINK.replace("{subject}", emailDetails.getSubject());
        WebElement emailItemLink = findByXpath(emailItemXpath);
        return emailItemLink;
    }

    public boolean isEmailDisplayedInList(EmailDetails emailDetails) {
        return getEmailDisplayedInList(emailDetails).isDisplayed();
    }

    public SentEmailFormPage openSentEmail(Users userTo, EmailDetails emailDetails) {
        getEmailDisplayedInList(emailDetails).click();
        return new SentEmailFormPage(getDriver());
    }

    public CreateEmailFormPage openDraftEmail(Users userTo, EmailDetails emailDetails) {
        getEmailDisplayedInList(emailDetails).click();
        return new CreateEmailFormPage(getDriver());
    }

    public SentEmailFormPage openReceivedEmail(Users userFrom, EmailDetails emailDetails) {
        getEmailDisplayedInList(emailDetails).click();
        return new SentEmailFormPage(getDriver());
    }

    public MailBoxPage openSent() {
        mailFoldersBlock.openSent();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage openDrafts() {
        mailFoldersBlock.openDrafts();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage openInbox() {
        mailFoldersBlock.openInbox();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage switchToEnglish() {
        footerBlock.openLanguageSwitcher();
        waitUntilDisplayed(languageSwitcherBlock);
        languageSwitcherBlock.selectEnglish();
        return new MailBoxPage(getDriver());
    }

}
