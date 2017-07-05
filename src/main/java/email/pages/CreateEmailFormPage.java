package email.pages;

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

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_FORM)
    private Link createEmailForm;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_TO_INPUT)
    private TextInput createEmailFormToInput;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_SUBJECT_INPUT)
    private TextInput createEmailFormSubjInput;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_BODY_FRAME)
    private WebElement createEmailFormBodyFrame;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_BODY_AREA)
    private TextInput createEmailFormBodyArea;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_SAVEDRAFT_BUTTON)
    private Button createEmailFormSaveDraftButton;

    @FindBy(xpath = CreateEmailFormPageLocators.CREATEEMAILFORM_SEND_BUTTON)
    private Button createEmailFormSendButton;

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
    }

    public void saveAsDraft() {
        createEmailFormSaveDraftButton.click();
        waitForPageToLoadAndVerifyBy(By.xpath(CreateEmailFormPageLocators.CREATEEMAILFORM_DRAFTTIME_TEXT));
    }

    public MailBoxPage openDrafts() {
        mailHomePaneBlock.openDrafts();
        return new MailBoxPage(getDriver());
    }

    public EmailSentPage sendEmail() {
        createEmailFormSendButton.click();
        return new EmailSentPage(getDriver());
    }

    public boolean isDraftEmailFormDataCorrect(Users userTo, EmailDetails emailDetails) {
        String emailToXpath = CreateEmailFormPageLocators.CREATEEMAILFORM_TO_INSERTED_TEXT;
        String emailToExpected = Users.getEmail(userTo);
        emailToXpath = emailToXpath.replace("{email}", emailToExpected);
        WebElement emailTo = findByXpath(emailToXpath);
        Utils.checkEquals("email 'To'", emailTo.getText(), emailToExpected);
        return true;
    }

}
