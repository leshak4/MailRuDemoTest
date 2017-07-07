package email.cases;

import email.data.Users;
import email.pages.MainPage;
import email.pages.OpenMainPage;
import email.utils.SettingsProperties;
import email.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class AbstractTest {

    protected WebDriver driver;
    private final Logger log = Logger.getLogger(this.getClass());

    @BeforeTest
    public void beforeRun() throws Exception {
        Utils.generateSalt();
        log.info("salt is: " + Utils.getSalt());
    }

    @BeforeMethod
    public void setUp() throws Exception {
        log.info("setting GoogleChrome as a working browser");
        System.setProperty("webdriver.chrome.driver", SettingsProperties.getProperty("pathToChromeDriver"));
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    public MainPage openMainPage() {
        OpenMainPage openMainPage = new OpenMainPage(driver);
        return openMainPage.openMainPage();
    }

    public void logTestHeader(final String text) {
        int aim = 64;
        String line = "";
        log.info("");
        for (int i = 0; i <= aim; i ++) {
            line = line + "*";
        }
        log.info(line);
        log.info(text);
        log.info(line);
    }

    public static String generateEmailString(Users user) {
        return user.getUsername() + user.getDomain();
    }

}
