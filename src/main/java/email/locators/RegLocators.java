package email.locators;

public class RegLocators {

    public static final String REG_REGISTRATION_BUTTON = "//*[@id='registrationForm']//button";

    public static final String REG_FIRSTNAME_INPUT = "//div[contains(@class, 'firstname')]//input";

    public static final String REG_LASTNAME_INPUT = "//div[contains(@class, 'lastname')]//input";

    public static final String REG_BIRTHDAY_DAY_DD = "//select[contains(@class, 'day')]";

    public static final String REG_BIRTHDAY_MONTH_DD = "//select[contains(@class, 'month')]";

    public static final String REG_BIRTHDAY_YEAR_DD = "//select[contains(@class, 'year')]";

    public static final String REG_CITY_INPUT = "//input[@id='your_town']";

    public static final String REG_SEX_RB = "//input[@type='radio']";

    public static final String REG_SEX_MAN_RB = "//input[@id='man1']";

    public static final String REG_SEX_FEMALE_RB = "//input[@id='man2']";

    public static final String REG_DESIRED_USERNAME_INPUT = "//*[@id='loginField']//input";

    public static final String REG_DESIRED_DOMAIN_DD = "//select[@name='RegistrationDomain']";

    public static final String REG_DESIRED_PASSWORD_INPUT = "//div[contains(@class, 'pass-row')]//input";

    public static final String REG_DESIRED_PASSWORD_REPEAT_INPUT = "//*[@id='signRePassword']//input";

    public static final String REG_SELECT_LANGUAGE_LINK = "//*[@id='FooterLangSwitcher']";

    public static final String REG_SELECT_LANGUAGE_DD = "//select[@id='langSwitcher']";

    public static final String REG_CONFIRM_LANGUAGE_SELECTION_BUTTON = "//*[@id='MailRuConfirm']//div[@class='is-changeLang_in']//button[contains(@class, 'confirm-ok')]";



}
