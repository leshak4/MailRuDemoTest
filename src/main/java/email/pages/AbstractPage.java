package email.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static org.junit.Assert.fail;

public abstract class AbstractPage {

    private final WebDriver driver;

    private final Logger log = Logger.getLogger(this.getClass());

    private static final int DEFAULT_TIMEOUT = 10;

    public AbstractPage(final WebDriver driver) {
        this.driver = driver;
    }

    // ***** Waits and sleep ***** //

    public void waitUntilDisplayed(final WebElement element) {
        waitUntilDisplayed(element, 120);
    }

    public void waitUntilDisplayed(final WebElement element, final Integer timeout) {
        waitUntilDisplayed(element, timeout, true);
    }

    private void waitUntilDisplayed(final WebElement element, final Integer timeout, final boolean isLog) {
        try {
            (new WebDriverWait(driver, timeout)).ignoring(StaleElementReferenceException.class)
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(final WebDriver d) {
                            if (element == null) {
                                return false;
                            } else {
                                return element.isDisplayed();
                            }
                        }
                    });
        } catch (final TimeoutException e) {
            if (isLog) {
                log.error("Element is not displayed, but expected!", e);
            }
            throw e;
        }
    }

    public void waitUntilDisplayed(final By locator) {
        waitUntilDisplayed(locator, DEFAULT_TIMEOUT);
    }

    public void waitUntilDisplayed(final By locator, final int timeout) {
        waitUntilDisplayed(locator, timeout, true);
    }

    private void waitUntilDisplayed(final By locator, final int timeout, final boolean isLog) {
        try {
            (new WebDriverWait(driver, timeout)).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (final TimeoutException e) {
            if (isLog) {
                log.error("Element is not displayed, but expected!", e);
            }
            throw e;
        }
    }

    public void waitUntilClickable(final By locator) {
        (new WebDriverWait(driver, DEFAULT_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilClickable(final WebElement element) {
        (new WebDriverWait(driver, DEFAULT_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver d) {
                if (element == null) {
                    return false;
                } else {
                    return element.isDisplayed() && element.isEnabled();
                }
            }
        });
    }

    public boolean isElementPresent(final By locator, final int timeout) {
        try {
            waitUntilDisplayed(locator, timeout, false);
            return true;
        } catch (final TimeoutException e) {
            return false;
        }
    }

    public boolean isElementPresent(final WebElement element, final int timeout) {
        try {
            waitUntilDisplayed(element, timeout, false);
            return true;
        } catch (final TimeoutException e) {
            return false;
        }
    }

    public boolean isElementPresent(final WebElement element) {
        try {
            waitUntilDisplayed(element, DEFAULT_TIMEOUT, false);
            return true;
        } catch (final TimeoutException e) {
            return false;
        }
    }

    public void waitUntilNotDisplayed(final By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilNotDisplayed(final By locator, final int timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void sleep(final long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (final InterruptedException e) {
            fail(e.getMessage());
        }
    }

    // ***** Find element by ***** //

    public WebElement findElement(final By locator, final Integer timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement findElement(final By locator) {
        return findElement(locator, DEFAULT_TIMEOUT);
    }

    public WebElement findById(final String id) {
        return findElement(By.id(id));
    }

    public WebElement findByCss(final String selector) {
        return findElement(By.cssSelector(selector));
    }

    public WebElement findByXpath(final String selector) {
        return findElement(By.xpath(selector));
    }

    public List<WebElement> findAllByXpath(final String selector) {
        return driver.findElements(By.xpath(selector));
    }

    // ***** Verifying page content ***** //

    /**
     * Verify page content
     *
     * @param pageIdentifier must be an element that 100% exist on this page and doesn't exist on others
     * @return if page is correct
     */
    @Step
    public boolean waitForPageToLoadAndVerifyBy(final By pageIdentifier) {
        final String pageName = this.getClass().getName().replace("email.pages.", "").replace("Page", "");
        log.info("Waiting for " + pageName + " page to load");
        if (isElementPresent(pageIdentifier, DEFAULT_TIMEOUT)) {
            log.info(pageName + " page is opened.");
            return true;
        } else {
            log.error("This is not " + pageName + " page. Something went wrong.");
            return false;
        }
    }

    @Step
    public boolean waitForPageToLoadAndVerifyWe(final WebElement pageIdentifier) {
        final String pageName = this.getClass().getName().replace("email.pages.", "").replace("Page", "");
        log.info("Waiting for " + pageName + " page to load");
        if (isElementPresent(pageIdentifier, DEFAULT_TIMEOUT)) {
            log.info(pageName + " page is opened.");
            return true;
        } else {
            log.error("This is not " + pageName + " page. Something went wrong.");
            return false;
        }
    }

    // ***** Switch to frame ***** //
    public void switchToFrame(final WebElement frame) {
        waitUntilDisplayed(frame); // just in case
        waitForFrameAndSwitch(frame);
        log.info("Switch to frame");
    }

    public void switchToDefaultContent() {
        log.info("Switch back to default content");
        getDriver().switchTo().defaultContent();
    }

    //experimental
    public void waitForFrameAndSwitch(final String frameId) {
        (new WebDriverWait(driver, 60)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
    }

    //experimental
    private void waitForFrameAndSwitch(final WebElement frame) {
        (new WebDriverWait(driver, 60)).until(new ExpectedCondition<WebDriver>() {
            @Override
            public WebDriver apply(final WebDriver driver) {
                try {
                    return driver.switchTo().frame(frame);
                } catch (final NoSuchFrameException e) {
                    return null;
                } catch (final NoSuchElementException e) {
                    return null;
                }
            }
        });
    }

    public void waitForFrameLoaded(final WebElement frame) {
        waitForFrameAndSwitch(frame);
        getDriver().switchTo().defaultContent();

    }

    // ***** getters & setters ***** //
    public WebDriver getDriver() {
        return driver;
    }

}
