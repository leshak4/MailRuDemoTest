package email.cases;

import email.data.Users;
import email.pages.AuthorizationPage;
import email.pages.LandingPage;
import email.utils.Utils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SignInTest extends AbstractTest{

    private final Logger log = Logger.getLogger(this.getClass());

    @Test(groups = { "signIn", "positive"} )
    public void successfulSignIn() {
        logTestHeader("Test: successful SignIn");
        log.info("TC: SignIn page: Logic: valid login and password");

        Users user = Users.MAIL_USER_1;
        LandingPage landing = openSignIn().correctSignIn(user);

        String expectedEmail = Utils.generateEmailString(user);
        String descr = "Correct email [" + expectedEmail + "] is displayed on the landing page";
        log.info("Check " + descr);
        assertThat(descr,
                landing.getLoggedEmailText(),
                equalToIgnoringCase(Utils.generateEmailString(user)));
        log.info("--PASSED--");
    }

    @Test(groups = { "signIn", "negative"} )
    public void tryToSignInWithWrongPassword() {
        logTestHeader("Test: try to login with wrong password");
        log.info("TC: SignIn page: Logic: invalid password");

        Users user = Users.MAIL_USER_WRONG_PSW;
        AuthorizationPage authorization = openSignIn().wrongSignIn(user);

        String descr = "Error message about wrong password is displayed";
        log.info("Check " + descr);
        assertTrue(descr, authorization.isWrongCredMsgDisplayed());
        log.info("--PASSED--");
    }
}
