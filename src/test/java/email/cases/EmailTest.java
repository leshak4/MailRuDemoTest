package email.cases;

import email.data.EmailDetails;
import email.data.TestDataProvider;
import email.data.Users;
import email.pages.*;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.*;

public class EmailTest extends AbstractTest{

    private final Logger log = Logger.getLogger(this.getClass());

    @Test(groups = { "login", "positive" },
            dataProvider = "getActiveUser",
            dataProviderClass = TestDataProvider.class)
    public void successfulSignIn(Users user) {
        logTestHeader("TC-P01: Log in with valid username and password");

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(user);

        String expectedEmail = Users.getEmail(user);
        String descr = "Email of a logged user [" + expectedEmail + "]";
        log.info("Check " + descr + " is displayed");
        assertThat(descr,
                mailBoxPage.getLoggedEmailText(),
                equalToIgnoringCase(expectedEmail));
        log.info("--PASSED--");
    }

    @Test(groups = { "login", "negative" } )
    public void tryToSignInWithWrongPassword() {
        logTestHeader("TC-N01: Try to login with invalid password");

        Users user = Users.getWrongUser();
        MainPage mainPage = openMainPage();
        SignInSimplePage signInSimplePage = mainPage.openSignInPage().wrongSignIn(user);

        String descr = "Error message about wrong username/password";
        log.info("Check " + descr + " is displayed");
        assertTrue(descr, signInSimplePage.isWrongCredMsgDisplayed());

        descr = "sign in area";
        log.info("Check " + descr + " is displayed");
        assertTrue(descr, signInSimplePage.isSignInAreaDisplayed());

        log.info("--PASSED--");
    }

    @Test(groups = { "createEmail", "positive" } )
    public void createAndSaveDraft() {
        logTestHeader("TC-P03: Create and save an email as draft");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getFirstEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);

        CreateEmailFormPage createEmailFormPage = mailBoxPage.openCreateEmailForm();
        createEmailFormPage.composeEmail(userTo, emailDetails);
        createEmailFormPage.saveAsDraft();

        mailBoxPage = createEmailFormPage.openDrafts();

        String descr = "the email in the 'drafts' folder";
        log.info("Check that " + descr + " is displayed");
        assertTrue(descr, mailBoxPage.isEmailDisplayedInList(emailDetails));

        log.info("--PASSED--");
    }

    @Test(groups = { "createEmail", "positive" } )
    public void validateDraftEmail() {
        logTestHeader("TC-P04: Validate details of previously saved email");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getFirstEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);

        CreateEmailFormPage createEmailFormPage = mailBoxPage.openCreateEmailForm();
        createEmailFormPage.composeEmail(userTo, emailDetails);
        createEmailFormPage.saveAsDraft();
        mailBoxPage = createEmailFormPage.openDrafts();

        mainPage = mailBoxPage.logout();
        signInPage = mainPage.openSignInPage();
        mailBoxPage = signInPage.signIn(userFrom).openDrafts().switchToEnglish();
        createEmailFormPage = mailBoxPage.openDraftEmail(userTo, emailDetails);

        String descr = "draft email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, createEmailFormPage.isDraftEmailFormDataCorrect(userTo, emailDetails));

        log.info("--PASSED--");
    }

    @Test(groups = { "sendEmail", "positive"} )
    public void sendEmailFromDraft() {
        logTestHeader("TC-P05: Send previously saved as draft email");

        Users userFrom = Users.getSecondUser();
        Users userTo = Users.getFirstUser();
        EmailDetails emailDetails = EmailDetails.getSecondEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);

        CreateEmailFormPage createEmailFormPage = mailBoxPage.openCreateEmailForm();
        createEmailFormPage.composeEmail(userTo, emailDetails);
        createEmailFormPage.saveAsDraft();
        mailBoxPage = createEmailFormPage.openDrafts();

        mainPage = mailBoxPage.logout();
        signInPage = mainPage.openSignInPage();
        mailBoxPage = signInPage.signIn(userFrom).openDrafts();
        createEmailFormPage = mailBoxPage.openDraftEmail(userTo, emailDetails);
        EmailSentPage emailSentPage = createEmailFormPage.sendEmail();

        String descr = "Confirmation that email is sent";
        log.info("Check that " + descr + " is displayed");
        assertTrue(descr, emailSentPage.isEmailSentConfirmationDisplayed());

        descr = "email address";
        log.info("Check that " + descr + " is displayed");
        assertEquals(Users.getEmail(userTo), emailSentPage.getDisplayedEmailTo());

        mailBoxPage = emailSentPage.openSent();

        descr = "the email in the 'sent' folder";
        log.info("Check that " + descr + " is displayed");
        assertTrue(descr, mailBoxPage.isEmailDisplayedInList(emailDetails));

        log.info("--PASSED--");
    }

    @Test(groups = { "createEmail", "sendEmail", "positive" } )
    public void createAndSendEmail() {
        logTestHeader("TC-P06: Create and send an email");

        Users userFrom = Users.getSecondUser();
        Users userTo = Users.getFirstUser();
        EmailDetails emailDetails = EmailDetails.getSecondEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);

        CreateEmailFormPage createEmailFormPage = mailBoxPage.openCreateEmailForm();

        createEmailFormPage.composeEmail(userTo, emailDetails);
        EmailSentPage emailSentPage = createEmailFormPage.sendEmail();

        String descr = "Confirmation that email is sent";
        log.info("Check that " + descr + " is displayed");
        assertTrue(descr, emailSentPage.isEmailSentConfirmationDisplayed());

        descr = "email address";
        log.info("Check that " + descr + " is displayed");
        assertEquals(Users.getEmail(userTo), emailSentPage.getDisplayedEmailTo());

        mailBoxPage = emailSentPage.openSent();

        descr = "the email in the 'sent' folder";
        log.info("Check that " + descr + " is displayed");
        assertTrue(descr, mailBoxPage.isEmailDisplayedInList(emailDetails));

        log.info("--PASSED--");
    }

    @Test(groups = { "sendEmail", "positive" } )
    public void validateSentEmail() {
        logTestHeader("TC-P07: Validate details of the sent previously email");

        Users userFrom = Users.getSecondUser();
        Users userTo = Users.getFirstUser();
        EmailDetails emailDetails = EmailDetails.getSecondEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);

        CreateEmailFormPage createEmailFormPage = mailBoxPage.openCreateEmailForm();
        createEmailFormPage.composeEmail(userTo, emailDetails);
        EmailSentPage emailSentPage = createEmailFormPage.sendEmail();
        mailBoxPage = emailSentPage.openSent();

        mainPage = mailBoxPage.logout();
        signInPage = mainPage.openSignInPage();
        mailBoxPage = signInPage.signIn(userFrom).openSent().switchToEnglish();
        SentEmailFormPage sentEmailFormPage = mailBoxPage.openSentEmail(userTo, emailDetails);

        String descr = "sent email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, sentEmailFormPage.isSentEmailFormDataCorrect(userFrom, userTo, emailDetails));
    }

    @Test(groups = { "sendEmail", "positive" } )
    public void validateReceivedEmail() {
        logTestHeader("TC-P08: Validate details of the received email");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getSecondEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);

        CreateEmailFormPage createEmailFormPage = mailBoxPage.openCreateEmailForm();
        createEmailFormPage.composeEmail(userTo, emailDetails);
        EmailSentPage emailSentPage = createEmailFormPage.sendEmail();
        mailBoxPage = emailSentPage.openSent();

        mainPage = mailBoxPage.logout();
        signInPage = mainPage.openSignInPage();
        mailBoxPage = signInPage.signIn(userTo).openInbox().switchToEnglish();
        SentEmailFormPage sentEmailFormPage = mailBoxPage.openReceivedEmail(userFrom, emailDetails);

        String descr = "received email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, sentEmailFormPage.isSentEmailFormDataCorrect(userFrom, userTo, emailDetails));
    }

    @Test(groups = { "login", "positive" },
            dataProvider = "getActiveUser",
            dataProviderClass = TestDataProvider.class)
    public void logout(Users user) {
        logTestHeader("TC-P09: Logout from personal account page");

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(user);

        mainPage = mailBoxPage.logout();

        String descr = "a logged user email";
        log.info("Check that " + descr + " is not displayed");
        assertFalse(descr, mainPage.isLoggedUserEmailDisplayed());
    }
}
