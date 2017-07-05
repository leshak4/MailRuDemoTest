package email.cases;

import email.data.TestDataProvider;
import email.data.UserCreds;
import email.data.Users;
import email.pages.*;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SignInTest extends AbstractTest{

    private final Logger log = Logger.getLogger(this.getClass());

    @Test(groups = { "signIn", "positive" },
            dataProvider = "getActiveUser",
            dataProviderClass = TestDataProvider.class)
    public void successfulSignIn(Users user) {
        logTestHeader("TC-P01: SignIn page: Valid login and password");

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage landing = signInPage.signIn(user);

        String expectedEmail = generateEmailString(user);
        String descr = "Email of a logged user [" + expectedEmail + "]";
        log.info("Check " + descr + " is displayed");
        assertThat(descr,
                landing.getLoggedEmailText(),
                equalToIgnoringCase(expectedEmail));
        log.info("--PASSED--");
    }

    @Test(groups = { "signIn", "negative" } )
    public void tryToSignInWithWrongPassword() {
        logTestHeader("TC-N01: SignIn page: Invalid password");

        Users user = Users.getWrongUser();
        MainPage mainPage = openMainPage();
        SignInSimplePage signInSimplePage = mainPage.openSignInPage().wrongSignIn(user);

        String descr = "Error message about wrong username/password";
        log.info("Check " + descr + " is displayed");
        assertTrue(descr, signInSimplePage.isWrongCredMsgDisplayed());
        log.info("--PASSED--");
    }

    @Test(groups = { "Registration", "positive" }, enabled = false)
    public void Registration() {
        logTestHeader("TC-P02: Registration page: new user successful registration");

        Users newUser = Users.getDynUser();
        String[] userDetails = UserCreds.MAIL_USER_DETAILS;

        MainPage mainPage = openMainPage();
        RegPage regPage = mainPage.openRegPage();
        regPage.fillRegistrationForm(newUser, userDetails);
    }

}
