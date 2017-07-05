package email.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final Logger log = Logger.getLogger("");

    public static String salt = null;

    //should be called once
    public static void generateSalt() {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMhhmmss");
        salt = dateFormat.format(d);
    }

    public static String getSalt() {
        return salt;
    }

    public static boolean checkEquals(String descr, String actualText, String expectedText) {
        boolean checkEquals = false;
        if (actualText.equals(expectedText)) {
            log.info(descr + " | strings are the same | " + actualText);
            checkEquals = true;
        } else {
            log.info(descr + " | strings are NOT the same | actual is [" + actualText + "] | expected is [" + expectedText + "]");
        }
        return checkEquals;
    }

    public static boolean checkContains(String descr, String whatText, String whereText) {
        boolean checkContains = false;
        if (whereText.contains(whatText)) {
            log.info(descr + " | [" + whereText + "] contains [" + whatText + "]");
            checkContains = true;
        } else {
            log.info(descr + " | [" + whereText + "] does NOT contain [" + whatText + "]");
        }
        return checkContains;
    }


}
