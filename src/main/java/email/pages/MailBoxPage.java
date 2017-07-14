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
    private WebElement mailboxLoggedEmailLink;

    @FindBy(xpath = MailBoxLocators.MAILBOX_LOGOUT_LINK)
    private Link mailboxLogoutLink;

    @FindBy(xpath = MailBoxLocators.MAILBOX_EMPTY_INDICATOR)
    private WebElement mailboxEmptyIndicator;

    @FindBy(xpath = MailBoxLocators.MAILBOX_FIRST_EMAIL_ITEM)
    private WebElement mailboxFirstEmailItem;

    public MailBoxPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.id(MailBoxLocators.MAILBOX_LOGGED_USER_EMAIL_LINK));
    }

    public String getLoggedEmailText() {
        return mailboxLoggedEmailLink.getText();
    }

    public Boolean isLoggedUserEmailDisplayed() {
        return isElementPresent(mailboxLoggedEmailLink, "Email of a logged user", 5);
    }

    public CreateEmailFormPage openCreateEmailForm() {
        log.info("'create email' form opening");
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
        log.info("opening of the sent email");
        waitUntilDisplayed(mailboxFirstEmailItem, 5);
        getEmailDisplayedInList(emailDetails).click();
        return new SentEmailFormPage(getDriver());
    }

    public CreateEmailFormPage openDraftEmail(Users userTo, EmailDetails emailDetails) {
        log.info("opening of the draft email");
        waitUntilDisplayed(mailboxFirstEmailItem, 5);
        getEmailDisplayedInList(emailDetails).click();
        return new CreateEmailFormPage(getDriver());
    }

    public SentEmailFormPage openReceivedEmail(Users userFrom, EmailDetails emailDetails) {
        log.info("opening of the received email");
        waitUntilDisplayed(mailboxFirstEmailItem, 5);
        getEmailDisplayedInList(emailDetails).click();
        return new SentEmailFormPage(getDriver());
    }

    public MailBoxPage openSent() {
        log.info("sent emails folder opening");
        waitUntilDisplayed(mailFoldersBlock, 5);
        mailFoldersBlock.openSent();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage openDrafts() {
        log.info("draft emails folder opening");
        waitUntilDisplayed(mailFoldersBlock, 5);
        mailFoldersBlock.openDrafts();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage openInbox() {
        log.info("inbox folder opening");
        waitUntilDisplayed(mailFoldersBlock, 5);
        mailFoldersBlock.openInbox();
        return new MailBoxPage(getDriver());
    }

    public MailBoxPage switchToEnglish() {
        log.info("switching to English version");
        footerBlock.openLanguageSwitcher();
        waitUntilDisplayed(languageSwitcherBlock);
        languageSwitcherBlock.selectEnglish();
        return new MailBoxPage(getDriver());
    }

    public MainPage logout() {
        log.info("logging out");
        mailboxLogoutLink.click();
        return new MainPage(getDriver());
    }

    public String checkMailBoxFolderEmpty() {
        String isMailBoxFolderEmpty = "not sure";
        boolean isEmptyBoxIconPresented = isElementPresent(mailboxEmptyIndicator, "empty box indicator", 3);
        boolean isFirstEmailItemPresented = isElementPresent(mailboxFirstEmailItem, "first email", 3);
        if (isEmptyBoxIconPresented & !isFirstEmailItemPresented) {
            log.info("mailbox folder is empty");
            isMailBoxFolderEmpty = "yes";
        }
        else if (!isEmptyBoxIconPresented & isFirstEmailItemPresented) {
            log.info("mailbox folder is not empty");
            isMailBoxFolderEmpty = "no";
        }
        else {
            log.info("something is wrong");
        }
        return isMailBoxFolderEmpty;
    }

}
