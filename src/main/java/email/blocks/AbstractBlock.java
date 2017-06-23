package email.blocks;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public abstract class AbstractBlock extends HtmlElement {

    private int DEFAULT_TIMEOUT = 10;

    public boolean isElementPresent(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }



}
