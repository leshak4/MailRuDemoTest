package email.locators;

public class SignInPromoLocators {

    public static final String SIGNINPROMO_WRONG_CRED_MSG_STR = "//*[@id='frame']//div[@class='b-login__errors'][contains(text(), 'Неверное имя пользователя или пароль')]";

    public static final String SIGNINPROMO_USERNAME_INPUT = "//input[@name='Username']";

    public static final String SIGNINPROMO_PASSWORD_INPUT = "//input[@name='Password']";

    public static final String SIGNINPROMO_ENTER_BUTTON = "//button/span[text()='Войти']";

    public static final String SIGNINPROMO_CREDS_FRAME = "//iframe[@class='ag-popup__frame__layout__iframe']";

    public static final String SIGNINPROMO_FOOTER_MAILRU_LINK = "//*[@id='w-portal-footer']//a/span[contains(text(), 'Mail')]";

}
