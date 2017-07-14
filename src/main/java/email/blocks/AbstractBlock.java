package email.blocks;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public abstract class AbstractBlock extends HtmlElement {

    private final Logger log = Logger.getLogger(this.getClass());

    private static final int DEFAULT_TIMEOUT = 10;

    public boolean isElementPresent(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public WebElement findByXpath(final String selector) {
        return getWrappedElement().findElement(By.xpath(selector));
    }


}
