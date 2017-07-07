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

    @Test(groups = { "signIn", "positive" } )
    public void logout() {

    }

    @Test(groups = { "createEmail", "positive" } )
    public void createAndSaveDraft() {
        logTestHeader("TC-P03: Create Email page: Create and save an email as draft");

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

    @Test(dependsOnMethods = { "createAndSaveDraft" }, groups = { "createEmail", "positive" } )
    public void validateDraftEmail() {
        logTestHeader("TC-P04: Draft Email page: validate details of saved previously email");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getFirstEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom).openDrafts().switchToEnglish();
        CreateEmailFormPage createEmailFormPage = mailBoxPage.openDraftEmail(userTo, emailDetails);

        String descr = "draft email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, createEmailFormPage.isDraftEmailFormDataCorrect(userTo, emailDetails));

        log.info("--PASSED--");
    }

    @Test(dependsOnMethods = { "createAndSaveDraft" }, groups = { "createEmail", "positive" } )
    public void sendEmailFromDraft() {
        logTestHeader("TC-P05: Draft Email page: send drafted email");

        log.info("--PASSED--");
    }

    @Test(groups = { "createEmail", "sendEmail", "positive" } )
    public void createAndSendEmail() {
        logTestHeader("TC-P06: Create Email page: Create and send an email");

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

    @Test(dependsOnMethods = { "createAndSendEmail" }, groups = { "createEmail", "sendEmail", "positive" } )
    public void validateSentEmail() {
        logTestHeader("TC-P07: Sent Email page: validate details of the sent previously email");

        Users userFrom = Users.getSecondUser();
        Users userTo = Users.getFirstUser();
        EmailDetails emailDetails = EmailDetails.getSecondEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);
        mailBoxPage.openSent().switchToEnglish();

        SentEmailFormPage sentEmailFormPage = mailBoxPage.openSentEmail(userTo, emailDetails);
        String descr = "sent email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, sentEmailFormPage.isSentEmailFormDataCorrect(userFrom, userTo, emailDetails));
    }

    @Test(dependsOnMethods = { "createAndSendEmail" }, groups = { "createEmail", "sendEmail", "positive" } )
    public void validateReceivedEmail() {
        logTestHeader("TC-P08: Inbox Email page: validate details of the received email");

        Users userFrom = Users.getSecondUser();
        Users userTo = Users.getFirstUser();
        EmailDetails emailDetails = EmailDetails.getSecondEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userTo);
        mailBoxPage.openInbox().switchToEnglish();

        SentEmailFormPage sentEmailFormPage = mailBoxPage.openReceivedEmail(userFrom, emailDetails);
        String descr = "received email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, sentEmailFormPage.isSentEmailFormDataCorrect(userFrom, userTo, emailDetails));
    }


}
