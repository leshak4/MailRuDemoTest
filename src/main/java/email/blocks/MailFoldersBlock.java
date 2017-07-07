package email.blocks;

import email.locators.CommonLocators;
import email.locators.MailFoldersBlockLocators;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;

@Name("Mail folders block")
@Block(@FindBy(xpath = CommonLocators.COMMON_MAIL_FOLDERS_BLOCK))
public class MailFoldersBlock extends AbstractBlock {

    @FindBy(xpath = MailFoldersBlockLocators.MAILFOLDERSBLOCK_OPEN_DRAFTS)
    private Link mailFoldersBlockOpenDraftsLink;

    @FindBy(xpath = MailFoldersBlockLocators.MAILFOLDERSBLOCK_OPEN_SENT)
    private Link mailFoldersBlockOpenSentLink;

    @FindBy(xpath = MailFoldersBlockLocators.MAILFOLDERSBLOCK_OPEN_INBOX)
    private Link mailFoldersBlockOpenInboxLink;

    public void openDrafts() {
        mailFoldersBlockOpenDraftsLink.click();
    }

    public void openSent() {
        mailFoldersBlockOpenSentLink.click();
    }

    public void openInbox() {
        mailFoldersBlockOpenInboxLink.click();
    }


}
