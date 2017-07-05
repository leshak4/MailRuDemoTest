package email.pages;

import email.locators.MainLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class MainPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    @FindBy(xpath = MainLocators.MAIN_BREADCRUMBS_POST_LINK)
    private Link mainBreadCrumbPostLink;

    @FindBy(xpath = MainLocators.MAIN_SEARCH_LINE_INPUT)
    private TextInput mainSearchLineInput;

    @FindBy(xpath = MainLocators.MAIN_REGISTRATION_LINK)
    private Link mainRegistrationLink;

    public MainPage(final WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(MainLocators.MAIN_SEARCH_LINE_INPUT));
    }

    public SignInPage openSignInPage() {
        mainBreadCrumbPostLink.click();
        return new SignInPage(getDriver());
    }

    public RegPage openRegPage() {
        mainRegistrationLink.click();
        return new RegPage(getDriver());
    }

}
