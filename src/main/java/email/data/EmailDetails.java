package email.data;

import email.utils.Utils;

public class EmailDetails {

    public static final String[] EMAIL_DETAILS_1 = {"Subject test 1 ", "Hi there! How are you? let's meet! ", "snapshot1.png", "132 KB"};

    public static final String[] EMAIL_DETAILS_2 = {"Subject test 2 ", "Hi Thomas! How are you? let's meet! ", "snapshot2.jpg", "267 KB"};

    static String[][] emailDetailsArray = {EMAIL_DETAILS_1, EMAIL_DETAILS_2};

    private String subject;

    private String mailBody;

    private String attachment;

    private String attachmentSize;

    public EmailDetails(final String subject, final String mailBody, final String attachment, final String attachmentSize) {
        this.subject = subject;
        this.mailBody = mailBody;
        this.attachment = attachment;
        this.attachmentSize = attachmentSize;
    }

    public String getSubject() {
        return subject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getAttachmentSize() {
        return attachmentSize;
    }

    public static EmailDetails getFirstEmailDetails() {
        return new EmailDetails(emailDetailsArray[0][0] + Utils.getSalt(), emailDetailsArray[0][1] + Utils.getSalt(), emailDetailsArray[0][2], emailDetailsArray[0][3]);
    }

    public static EmailDetails getSecondEmailDetails() {
        return new EmailDetails(emailDetailsArray[1][0] + Utils.getSalt(), emailDetailsArray[1][1] + Utils.getSalt(), emailDetailsArray[1][2], emailDetailsArray[1][3]);
    }

}
