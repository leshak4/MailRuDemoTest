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

    public static final String CREATEEMAILFORM_TO_INSERTED_TEXT = "//*[contains(@data-text, '@')]/span[contains(@class, 'text')]";

    public static final String CREATEEMAILFORM_ATTACHE_FILE_LINK = "//input[@name='Filedata']/..//span";

    public static final String CREATEEMAILFORM_ATTACHE_FILE_INPUT = "//input[@name='Filedata']";

    public static final String CREATEEMAILFORM_ATTACHE_FILE_GENERAL = "//*[contains(@class, 'input-file compose-attachments')]";

    public static final String CREATEEMAILFORM_ATTACHED_FILE_PREVIEW = "//*[@class='b-thumb__image']";

    public static final String CREATEEMAILFORM_ATTACHED_FILE_NAME = "//*[contains(@class, 'b-thumb_compose')]//*[@class='b-filename__name']";

    public static final String CREATEEMAILFORM_ATTACHED_FILE_EXT = "//*[contains(@class, 'b-thumb_compose')]//*[@class='b-filename__extension']";

    public static final String CREATEEMAILFORM_ATTACHED_FILE_FULLNAME = "//*[contains(@class, 'b-thumb_compose')]//*[@class='b-filename__text']";

    public static final String CREATEEMAILFORM_ATTACHED_FILE_SIZE = "//*[contains(@class, 'btn btn_bare')]/*[@class='btn__text']";



}
