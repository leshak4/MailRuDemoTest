package email.locators;

public class MailBoxLocators {

    public static final String MAILBOX_LOGGED_USER_EMAIL_LINK = "PH_user-email";

    public static String MAILBOX_EMAIL_ITEM_LINK = "//*[contains(@class, 'info')]//*[contains(@class, 'subj')][text()='{subject}']/../../*[contains(@class, 'addr')][text()='{email}']";

}
