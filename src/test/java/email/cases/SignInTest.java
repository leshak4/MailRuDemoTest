package email.cases;

import email.data.Users;
import email.pages.LandingPage;
import email.pages.MainPage;
import email.pages.SignInPromoPage;
import email.pages.SignInSimplePage;
import email.utils.Utils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SignInTest extends AbstractTest{

    private final Logger log = Logger.getLogger(this.getClass());

    @Test(groups = { "signIn", "positive" } )
    public void successfulSignIn() {
        logTestHeader("Test: successful sign in");
        log.info("TC-P01: SignIn page: Logic: valid login and password");

        Users user = Users.MAIL_USER_1;
        MainPage mainPage = openMainPage();
        SignInPromoPage signInPromoPage = mainPage.openSignInPage();
        LandingPage landing = signInPromoPage.correctSignInPromo(user);

        String expectedEmail = Utils.generateEmailString(user);
        String descr = "Correct email [" + expectedEmail + "] is displayed on the landing page";
        log.info("Check " + descr);
        assertThat(descr,
                landing.getLoggedEmailText(),
                equalToIgnoringCase(expectedEmail));
        log.info("--PASSED--");
    }

    @Test(groups = { "signIn", "negative" } )
    public void tryToSignInWithWrongPassword() {
        logTestHeader("Test: try to sign in with wrong password");
        log.info("TC-N01: SignIn page: Logic: invalid password");

        Users user = Users.MAIL_USER_WRONG_PSW;
        MainPage mainPage = openMainPage();
        SignInSimplePage signInSimplePage = mainPage.openSignInPage().wrongSignInPromo(user);

        String descr = "Error message about wrong username/password";
        log.info("Check " + descr);
        assertTrue(descr, signInSimplePage.isWrongCredMsgDisplayed());
        log.info("--PASSED--");
    }
}
