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
        StringBuffer header = new StringBuffer(text);
        log.info("");
        log.info("************************************************************");
        int len = header.length();
        int aim = 64;
        while (len > 0) {
            if (len > 64) {
                final String temp = header.substring(0, aim);
                log.info("* " + temp + " *");
                header = header.delete(0, aim);
                len = header.length();
            } else {
                final StringBuffer temp = header;
                while (temp.length() < aim) {
                    temp.append(' ');
                }
                log.info("* " + temp + " *");
                len = 0;
            }
        }
        log.info("************************************************************");
    }

    public static String generateEmailString(Users user) {
        return user.getUsername() + user.getDomain();
    }

}
