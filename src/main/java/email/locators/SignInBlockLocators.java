package email.locators;

public class SignInBlockLocators {

    public static final String SIGNIN_BLOCK_WRONG_CRED_MSG_STR = "//div[@class='b-login__errors'][contains(text(), 'Неверное имя пользователя или пароль')]";

    public static final String SIGNIN_BLOCK_USERNAME_INPUT = "//input[@name='Username']";

    public static final String SIGNIN_BLOCK_PASSWORD_INPUT = "//input[@name='Password']";

    public static final String SIGNIN_BLOCK_ENTER_BUTTON = "//button/span[text()='Войти']";

}
