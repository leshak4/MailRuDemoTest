package email.blocks;

import email.locators.CommonLocators;
import email.locators.LanguageSwitcherBlockLocators;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Select;

@Name("Language switcher block")
@Block(@FindBy(xpath = CommonLocators.COMMON_LANGUAGE_SWITCHER_BLOCK))
public class LanguageSwitcherBlock extends AbstractBlock {

    @FindBy(xpath = LanguageSwitcherBlockLocators.LANGUAGESWITCHERBLOCK_SELECTOR)
    private Select languageSwitcherSelector;

    @FindBy(xpath = LanguageSwitcherBlockLocators.LANGUAGESWITCHERBLOCK_CONFIRM_BUTTON)
    private Button languageSwitcherConfirmButton;

    public void selectEnglish() {
        languageSwitcherSelector.selectByVisibleText("English");
        languageSwitcherConfirmButton.click();
    }

}
