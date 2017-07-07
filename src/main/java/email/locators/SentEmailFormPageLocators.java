package email.locators;

public class SentEmailFormPageLocators {

    public static final String SENTEMAILFORM_SUBJECT = "//*[@class='b-letter__head__subj__text']";

    public static final String SENTEMAILFORM_EMAILFROM = "//*[@data-mnemo='from']/span";

    public static final String SENTEMAILFORM_EMAILTO = "//*[@data-mnemo='to']";

    public static final String SENTEMAILFORM_MESSAGEBODY = "//*[@class='b-letter__body']//div[contains(@id, 'BODY')]";

    public static final String SENTEMAILFORM_ATTACHED_FILE_PREVIEW = "//*[@class='js-previewImg']";

    public static final String SENTEMAILFORM_ATTACHED_FILE_NAME = "//span[contains(@class, 'name__filename')]";

    public static final String SENTEMAILFORM_ATTACHED_FILE_EXT = "//span[contains(@class, 'name__filetype')]";

    public static final String SENTEMAILFORM_ATTACHED_FILE_SIZE = "//div[@class='js-info']/span[1]";



}
