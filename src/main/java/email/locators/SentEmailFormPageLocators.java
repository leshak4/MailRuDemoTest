package email.locators;

public class SentEmailFormPageLocators {

    public static final String SENTEMAILFORM_SUBJECT = "//*[@class='b-letter__head__subj__text']";

    public static final String SENTEMAILFORM_EMAILFROM = "//*[@data-mnemo='from']/span";

    public static final String SENTEMAILFORM_EMAILTO = "//*[@data-mnemo='to']";

    public static final String SENTEMAILFORM_MESSAGEBODY = "//*[@class='b-letter__body']//div[contains(@id, 'BODY')]";



}
