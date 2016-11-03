package email.utils;

import email.data.Users;
import org.apache.log4j.Logger;

public class Utils {

    private static final Logger log = Logger.getLogger("Utils");

    public static String generateEmailString(Users user) {
        return user.getUsername() + user.getDomain();
    }

}
