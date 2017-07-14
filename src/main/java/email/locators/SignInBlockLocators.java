package email.locators;

public class SignInBlockLocators {

    public static final String SIGNIN_BLOCK_WRONG_CRED_MSG_STR = "//div[@class='b-login__errors']";

    public static final String SIGNIN_BLOCK_USERNAME_INPUT = "//input[@name='Username']";

    public static final String SIGNIN_BLOCK_PASSWORD_INPUT = "//input[@name='Password']";

    public static final String SIGNIN_BLOCK_ENTER_BUTTON = "//button/span[text()='Войти']";

    public static final String SIGNIN_BLOCK_DROPDOWN_ARROW_WE = "//*[@class='b-dropdown__arrow']";

    public static String SIGNIN_BLOCK_DROPDOWN_DOMAIN_LINK = "//a/span[text()='{domainName}']";

}
