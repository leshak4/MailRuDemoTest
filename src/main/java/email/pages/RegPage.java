package email.pages;

import email.data.Users;
import email.locators.RegLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.*;

public class RegPage extends AbstractPage {

    private final Logger log = Logger.getLogger(this.getClass());

    @FindBy(xpath = RegLocators.REG_FIRSTNAME_INPUT)
    private TextInput regFirstNameInput;

    @FindBy(xpath = RegLocators.REG_LASTNAME_INPUT)
    private TextInput regLastNameInput;

    @FindBy(xpath = RegLocators.REG_CITY_INPUT)
    private TextInput regCityInput;

    @FindBy(xpath = RegLocators.REG_BIRTHDAY_DAY_DD)
    private Select regBirthDayDD;

    @FindBy(xpath = RegLocators.REG_BIRTHDAY_MONTH_DD)
    private Select regBirthMonthDD;

    @FindBy(xpath = RegLocators.REG_BIRTHDAY_YEAR_DD)
    private Select regBirthYearDD;

    @FindBy(xpath = RegLocators.REG_SEX_RB)
    private Radio regSexRB;

    @FindBy(xpath = RegLocators.REG_SEX_MAN_RB)
    private WebElement regSexManRB;

    @FindBy(xpath = RegLocators.REG_SEX_FEMALE_RB)
    private WebElement regSexFemaleRB;

    @FindBy(xpath = RegLocators.REG_DESIRED_USERNAME_INPUT)
    private TextInput regUsernameInput;

    @FindBy(xpath = RegLocators.REG_DESIRED_PASSWORD_INPUT)
    private TextInput regPasswordInput;

    @FindBy(xpath = RegLocators.REG_DESIRED_PASSWORD_REPEAT_INPUT)
    private TextInput regPasswordRepeatInput;

    @FindBy(xpath = RegLocators.REG_DESIRED_DOMAIN_DD)
    private Select regDomainDD;

    @FindBy(xpath = RegLocators.REG_REGISTRATION_BUTTON)
    private Button regRegistrationButton;

    @FindBy(xpath = RegLocators.REG_SELECT_LANGUAGE_LINK)
    private Link regSelectLanguageLink;

    @FindBy(xpath = RegLocators.REG_SELECT_LANGUAGE_DD)
    private Select regSelectLanguageDD;

    @FindBy(xpath = RegLocators.REG_CONFIRM_LANGUAGE_SELECTION_BUTTON)
    private Button regConfirmLanguageButton;

    public RegPage(WebDriver driver) {
        super(driver);
        waitForPageToLoadAndVerifyBy(By.xpath(RegLocators.REG_REGISTRATION_BUTTON));
    }

    public void fillRegistrationForm(Users user, String[] userDetails) {
        log.info("registration form filling");
        regFirstNameInput.sendKeys(userDetails[0]);
        regLastNameInput.sendKeys(userDetails[1]);
        regBirthDayDD.selectByIndex(Integer.parseInt(userDetails[2]));
        regBirthMonthDD.selectByIndex(Integer.parseInt(userDetails[3]));
        regBirthYearDD.selectByValue(userDetails[4]);
        regUsernameInput.sendKeys(user.getUsername());
        regPasswordInput.sendKeys(user.getPassword());
        regPasswordRepeatInput.sendKeys(user.getPassword());
        regDomainDD.selectByVisibleText(user.getDomain());
        if (userDetails[5].equalsIgnoreCase("M")) {
            regSexRB.selectByValue("1");
        } else if (userDetails[5].equalsIgnoreCase("F")) {
            regSexRB.selectByValue("2");
        }
        regRegistrationButton.click();
    }
}
