package email.locators;

public class AuthorizationLocators {

    public static final String AUTH_FORM_HEADER_STR = "//*[@id='frame']//div[@class='b-login__header__title']";

    public static final String AUTH_FORM_WRONG_CRED_MSG_STR = "//*[@id='frame']//div[@class='b-login__errors'][contains(text(), 'Неверное имя пользователя или пароль')]";

}
