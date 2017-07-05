package email.cases;

import email.data.EmailDetails;
import email.data.Users;
import email.pages.*;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmailTest extends AbstractTest{

    private final Logger log = Logger.getLogger(this.getClass());

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
        mailBoxPage.isEmailDisplayedInList(userTo, emailDetails);

        String descr = "the email in the 'drafts' folder";
        log.info("Check that " + descr + " is displayed");
        assertTrue(descr, mailBoxPage.isEmailDisplayedInList(userTo, emailDetails));

        log.info("--PASSED--");
    }

    @Test(dependsOnMethods = { "createAndSaveDraft" }, groups = { "createEmail", "positive" } )
    public void validateDraftEmail() {
        logTestHeader("TC-P04: Create Email page: validate details of saved previously email");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getFirstEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);
        mailBoxPage.openDrafts();
        CreateEmailFormPage createEmailFormPage = mailBoxPage.openDraftEmail(userTo, emailDetails);
        Boolean isDraftEmailFormDataCorrect = createEmailFormPage.isDraftEmailFormDataCorrect(userTo, emailDetails);

    }

    @Test(groups = { "createEmail", "sendEmail", "positive" } )
    public void createAndSendEmail() {
        logTestHeader("TC-P05: Create Email page: Create and send an email");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getFirstEmailDetails();

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
        assertTrue(descr, mailBoxPage.isEmailDisplayedInList(userTo, emailDetails));

        log.info("--PASSED--");
    }

    @Test(dependsOnMethods = { "createAndSendEmail" }, groups = { "createEmail", "sendEmail", "positive" } )
    public void validateSentEmail() {
        logTestHeader("TC-P06: openSentEmail Email page: validate details of the sent previously email");

        Users userFrom = Users.getFirstUser();
        Users userTo = Users.getSecondUser();
        EmailDetails emailDetails = EmailDetails.getFirstEmailDetails();

        MainPage mainPage = openMainPage();
        SignInPage signInPage = mainPage.openSignInPage();
        MailBoxPage mailBoxPage = signInPage.signIn(userFrom);
        mailBoxPage.openSent();

        SentEmailFormPage sentEmailFormPage = mailBoxPage.openSentEmail(userTo, emailDetails);
        String descr = "sent email details";
        log.info("Check that " + descr + " are valid");
        assertTrue(descr, sentEmailFormPage.isSentEmailFormDataCorrect(userFrom, userTo, emailDetails));
    }


}
