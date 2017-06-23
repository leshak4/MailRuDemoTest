package email.elements;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class AbstractElement extends TypifiedElement {

    public AbstractElement(final WebElement element) {
        super(element);
    }

}
