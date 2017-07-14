package email.locators;

public class MailBoxLocators {

    public static final String MAILBOX_LOGGED_USER_EMAIL_LINK = "PH_user-email";

    public static String MAILBOX_EMAIL_ITEM_LINK = "//div[contains(@data-cache-key, 'undefined')][not(contains(@style, 'none'))]//a[@data-subject='{subject}'] ";

    public static final String MAILBOX_LOGOUT_LINK = "//*[@id='PH_logoutLink']";

    public static final String MAILBOX_FIRST_EMAIL_ITEM = "//div[contains(@data-cache-key, 'undefined')][not(contains(@style, 'none'))]//*[contains(@class, 'b-datalist__item js-datalist-item')][1]//*[@class='b-datalist__item__body']/a";

    public static final String MAILBOX_EMPTY_INDICATOR = "//div[contains(@data-cache-key, 'undefined')][@style='']//div[not(contains(@hidden, 'hidden'))]/span[@class='b-datalist__empty__text']";

}
