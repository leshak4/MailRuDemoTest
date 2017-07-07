package email.blocks;

import email.locators.CommonLocators;
import email.locators.MailHomePaneBlockLocators;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;

@Name("Top Home Pane Block")
@Block(@FindBy(xpath = CommonLocators.COMMON_MAIL_HOME_PANE_BLOCK))
public class MailHomePaneBlock extends AbstractBlock {

    @FindBy(xpath = MailHomePaneBlockLocators.MAILHOMEPANEBLOCK_CREATE_EMAIL)
    private Link mailHomePaneBlockCreateEmailLink;

    public void openCreateEmailForm() {
        mailHomePaneBlockCreateEmailLink.click();
    }

}
