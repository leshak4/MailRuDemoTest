package email.locators;

public class SignInLocators {

    public static final String SIGNIN_WRONG_CRED_MSG_STR = "//*[@id='frame']//div[@class='b-login__errors']";

    public static final String SIGNIN_USERNAME_INPUT = "//input[@name='Username']";

    public static final String SIGNIN_PASSWORD_INPUT = "//input[@name='Password']";

    public static final String SIGNIN_ENTER_BUTTON = "//button/span[text()='Войти']";

    public static final String SIGNIN_CREDS_FRAME = "//iframe[@class='ag-popup__frame__layout__iframe']";

    public static final String SIGNIN_FOOTER_MAILRU_LINK = "//*[@id='w-portal-footer']//a/span[contains(text(), 'Mail')]";

}
