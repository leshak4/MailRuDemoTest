package email.locators;

public class CreateEmailFormPageLocators {

    public static final String CREATEEMAILFORM_FORM = "//form[@name='Compose']";

    public static final String CREATEEMAILFORM_TO_INPUT = "//form[@name='Compose']//textarea[@data-original-name='To']";

    public static final String CREATEEMAILFORM_SUBJECT_INPUT = "//form[@name='Compose']//input[@name='Subject']";

    public static final String CREATEEMAILFORM_BODY_FRAME = "//td[contains(@class, 'mceIframeContainer')]/iframe";

    public static final String CREATEEMAILFORM_BODY_AREA = "//*[@id='tinymce']";

    public static final String CREATEEMAILFORM_SAVEDRAFT_BUTTON = "//*[@data-name='saveDraft']";

    public static final String CREATEEMAILFORM_DRAFTTIME_TEXT = "//span[@class='time']";

    public static final String CREATEEMAILFORM_SEND_BUTTON = "//*[@data-name='send']";

    public static final String CREATEEMAILFORM_TO_INSERTED_TEXT = "//input[@name='To']/..//span[contains(@data-text, '{email}')]";



}
