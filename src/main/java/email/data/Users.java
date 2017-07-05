package email.data;

import email.utils.Utils;

import static email.data.UserCreds.*;

public class Users {

    private String username;

    private String password;

    private String domain;

    public Users(final String username, final String password, final String domain) {
        this.username = username;
        this.password = password;
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomain() {
        return domain;
    }

    public static Users getDynUser() {
        return new Users(MAIL_USER_COMMON_PART[0] + Utils.getSalt(),
                MAIL_USER_COMMON_PART[1] + Utils.getSalt(),
                MAIL_USER_COMMON_PART[2]);
    }

    public static Users getFirstUser() {
        return new Users(MAIL_USER_1[0],
                MAIL_USER_1[1],
                MAIL_USER_1[2]);
    }

    public static Users getSecondUser() {
        return new Users(MAIL_USER_2[0],
                MAIL_USER_2[1],
                MAIL_USER_2[2]);
    }

    public static Users getWrongUser() {
        return new Users(MAIL_USER_WRONG_PSW[0],
                MAIL_USER_WRONG_PSW[1],
                MAIL_USER_WRONG_PSW[2]);
    }

    public static String getEmail(Users user) {
        return user.getUsername() + user.getDomain();
    }


}
