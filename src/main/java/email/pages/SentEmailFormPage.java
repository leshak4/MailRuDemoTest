package email.pages;

import email.blocks.MailHomePaneBlock;
import email.data.EmailDetails;
import email.data.Users;
import email.locators.SentEmailFormPageLocators;
import email.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentEmailFormPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    private MailHomePaneBlock mailHomePaneBlock;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_SUBJECT)
    private WebElement sentEmailSubject;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_EMAILTO)
    private WebElement sentEmailTo;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_EMAILFROM)
    private WebElement sentEmailFrom;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_MESSAGEBODY)
    private WebElement sentEmailBody;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_ATTACHED_FILE_PREVIEW)
    private WebElement sentEmailAttachedFilePreview;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_ATTACHED_FILE_NAME)
    private WebElement sentEmailAttachedFileName;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_ATTACHED_FILE_EXT)
    private WebElement sentEmailAttachedFileExt;

    @FindBy(xpath = SentEmailFormPageLocators.SENTEMAILFORM_ATTACHED_FILE_SIZE)
    private WebElement sentEmailAttachedFileSize;

    public SentEmailFormPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(SentEmailFormPageLocators.SENTEMAILFORM_SUBJECT));
    }

    public boolean isSentEmailFormDataCorrect(Users userFrom, Users userTo, EmailDetails emailDetails) {
        boolean isEmailFormDataCorrect = true;
        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkEquals("sent email subject", sentEmailSubject.getText(), emailDetails.getSubject());
        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkContains("sent email To field", Users.getEmail(userTo), sentEmailTo.getText());
        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkContains("sent email From field", Users.getEmail(userFrom), sentEmailFrom.getText());
        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkContains("sent email From field", userFrom.getFirstname() + " " + userFrom.getLastname(), sentEmailFrom.getText());
        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkEquals("sent email body", sentEmailBody.getText(), emailDetails.getMailBody());

        if (emailDetails.getAttachment().trim().length() > 0) {
            waitForPageToLoadAndVerifyWe(sentEmailAttachedFilePreview);
            isEmailFormDataCorrect = isEmailFormDataCorrect &
                    isElementPresent(sentEmailAttachedFilePreview, "attached file preview", 5);
            String attachmentName = sentEmailAttachedFileName.getText() + sentEmailAttachedFileExt.getText();
            isEmailFormDataCorrect = isEmailFormDataCorrect &
                    Utils.checkEquals("attached file name", attachmentName, emailDetails.getAttachment());
            isEmailFormDataCorrect = isEmailFormDataCorrect &
                    Utils.checkEquals("attached file size", sentEmailAttachedFileSize.getText(), emailDetails.getAttachmentSize());
        }

        return isEmailFormDataCorrect;
    }


}
