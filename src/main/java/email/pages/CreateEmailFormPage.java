package email.pages;

import email.blocks.MailFoldersBlock;
import email.blocks.MailHomePaneBlock;
import email.data.EmailDetails;
import email.data.Users;
import email.locators.CreateEmailFormPageLocators;
import email.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreateEmailFormPage extends AbstractPage {

    private MailHomePaneBlock mailHomePaneBlock;

    private MailFoldersBlock mailFoldersBlock;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_FORM)
    private Link createEmailForm;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_TO_INPUT)
    private TextInput createEmailFormToInput;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_SUBJECT_INPUT)
    private WebElement createEmailFormSubjInput;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_BODY_FRAME)
    private WebElement createEmailFormBodyFrame;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_BODY_AREA)
    private WebElement createEmailFormBodyArea;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_SAVEDRAFT_BUTTON)
    private Button createEmailFormSaveDraftButton;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_SEND_BUTTON)
    private Button createEmailFormSendButton;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHE_FILE_LINK)
    private Link createEmailFormAttacheLink;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHE_FILE_INPUT)
    private WebElement createEmailFormAttacheInput;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHE_FILE_GENERAL)
    private WebElement createEmailFormAttacheGeneral;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHED_FILE_PREVIEW)
    private WebElement createEmailFormAttachedFilePreview;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHED_FILE_NAME)
    private WebElement createEmailFormAttachedFileName;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHED_FILE_EXT)
    private WebElement createEmailFormAttachedFileExt;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHED_FILE_FULLNAME)
    private WebElement createEmailFormAttachedFileFullname;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_ATTACHED_FILE_SIZE)
    private WebElement createEmailFormAttachedFileSize;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_TO_INSERTED_TEXT)
    private WebElement createEmailFormToInsertedText;


    public CreateEmailFormPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(CreateEmailFormPageLocators.CREATEEMAILFORM_FORM));
    }

    public void composeEmail(Users user, EmailDetails emailDetails) {
        createEmailFormToInput.sendKeys(Users.getEmail(user));
        createEmailFormSubjInput.sendKeys(emailDetails.getSubject());
        switchToFrame(createEmailFormBodyFrame);
        createEmailFormBodyArea.clear();
        createEmailFormBodyArea.sendKeys(emailDetails.getMailBody());
        switchFromFrameToDefault();
        if (emailDetails.getAttachment().trim().length() > 0) {
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\testData\\attachments\\" + emailDetails.getAttachment();
            createEmailFormAttacheGeneral.click();
            Utils.selectFileFromExplorer(path);
            waitForPageToLoadAndVerifyWe(createEmailFormAttachedFilePreview);
        }
    }

    public void saveAsDraft() {
        createEmailFormSaveDraftButton.click();
        waitForPageToLoadAndVerifyBy(By.xpath(CreateEmailFormPageLocators.CREATEEMAILFORM_DRAFTTIME_TEXT));
    }

    public MailBoxPage openDrafts() {
        mailFoldersBlock.openDrafts();
        return new MailBoxPage(getDriver());
    }

    public EmailSentPage sendEmail() {
        createEmailFormSendButton.click();
        return new EmailSentPage(getDriver());
    }

    public boolean isDraftEmailFormDataCorrect(Users userTo, EmailDetails emailDetails) {

        waitForPageToLoadAndVerifyWe(createEmailFormToInsertedText);

        boolean isEmailFormDataCorrect = true;

        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkEquals("Text in 'To' field", createEmailFormToInsertedText.getText(), Users.getEmail(userTo));

        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkEquals("Subject", createEmailFormSubjInput.getAttribute("value"), emailDetails.getSubject());

        switchToFrame(createEmailFormBodyFrame);
        isEmailFormDataCorrect = isEmailFormDataCorrect &
                Utils.checkEquals("Email body", createEmailFormBodyArea.getText(), emailDetails.getMailBody());
        switchFromFrameToDefault();

        if (emailDetails.getAttachment().trim().length() > 0) {
            isEmailFormDataCorrect = isEmailFormDataCorrect &
                    isElementPresent(createEmailFormAttachedFilePreview, "attachment preview");

            Utils.mouseOver(createEmailFormAttachedFilePreview, getDriver());

            String actualName = createEmailFormAttachedFileName.getText() + createEmailFormAttachedFileExt.getText();
            isEmailFormDataCorrect = isEmailFormDataCorrect &
                    Utils.checkEquals("name of attached file", actualName, emailDetails.getAttachment());
            isEmailFormDataCorrect = isEmailFormDataCorrect &
                    Utils.checkEquals("size of attached file", createEmailFormAttachedFileSize.getText(), emailDetails.getAttachmentSize());
        }

        return isEmailFormDataCorrect;
    }

}
