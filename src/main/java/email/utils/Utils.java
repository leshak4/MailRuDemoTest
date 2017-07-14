package email.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.fail;

public class Utils {

    private static final Logger log = Logger.getLogger("");

    public static String salt = null;

    //should be called once
    public static void generateSalt() {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMhhmmss");
        salt = dateFormat.format(d);
        log.info("=== salt is: " + Utils.getSalt());
    }

    public static String getSalt() {
        return salt;
    }

    public static boolean checkEquals(String descr, String actualText, String expectedText) {
        descr = descr + " validation";
        boolean checkEquals = false;
        if (actualText.equals(expectedText)) {
            log.info(descr + " | [pass] strings are the same | " + actualText);
            checkEquals = true;
        } else {
            log.info(descr + " | [fail] strings are NOT the same | actual is [" + actualText + "] | expected is [" + expectedText + "]");
        }
        return checkEquals;
    }

    public static boolean checkContains(String descr, String whatText, String whereText) {
        descr = descr + " validation";
        boolean checkContains = false;
        if (whereText.contains(whatText)) {
            log.info(descr + " | [pass] [" + whereText + "] contains [" + whatText + "]");
            checkContains = true;
        } else {
            log.info(descr + " | [fail] [" + whereText + "] does NOT contain [" + whatText + "]");
        }
        return checkContains;
    }

    public static void selectFileFromExplorer(String path) {
        sleep(1000);
        StringSelection ss = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void sleep(final long msec) {
        try {
            Thread.sleep(msec);
        } catch (final InterruptedException e) {
            fail(e.getMessage());
        }
    }

    public static void mouseOver(WebElement element, WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        sleep(200);
    }


}
