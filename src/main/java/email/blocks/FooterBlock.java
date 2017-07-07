package email.blocks;

import email.locators.CommonLocators;
import email.locators.FooterBlockLocators;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Link;

@Name("Footer block")
@Block(@FindBy(xpath = CommonLocators.COMMON_FOOTER_BLOCK))
public class FooterBlock extends AbstractBlock {

    @FindBy(xpath = FooterBlockLocators.FOOTERBLOCK_LANGUAGE_SWITCHER_LINK)
    private Link footerBlockLanguageSwitcherLink;

    public void openLanguageSwitcher() {
        footerBlockLanguageSwitcherLink.click();
    }


}
