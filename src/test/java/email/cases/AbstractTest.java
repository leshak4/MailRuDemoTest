package email.cases;

import email.pages.MainPage;
import email.pages.OpenMainPage;
import email.utils.SettingsProperties;
import email.utils.Utils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {

    protected WebDriver driver;
    private final Logger log = Logger.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() throws Exception {
        log.info("setting GoogleChrome as a working browser");
        System.setProperty("webdriver.chrome.driver", SettingsProperties.getProperty("pathToChromeDriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Utils.generateSalt();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
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

}
