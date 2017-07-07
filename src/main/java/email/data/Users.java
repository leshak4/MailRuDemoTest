package email.data;

import static email.data.UserCreds.*;

public class Users {

    private String username;
    private String password;
    private String domain;
    private String firstname;
    private String lastname;
    private String alias;

    public Users(final String username, final String password, final String domain, final String firstname, final String lastname, final String alias) {
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.firstname = firstname;
        this.lastname = lastname;
        this.alias = alias;
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

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAlias() {
        return alias;
    }

    public static Users getFirstUser() {
        return new Users(MAIL_USER_1[0],
                MAIL_USER_1[1],
                MAIL_USER_1[2],
                MAIL_USER_1[3],
                MAIL_USER_1[4],
                MAIL_USER_1[5]);
    }

    public static Users getSecondUser() {
        return new Users(MAIL_USER_2[0],
                MAIL_USER_2[1],
                MAIL_USER_2[2],
                MAIL_USER_2[3],
                MAIL_USER_2[4],
                MAIL_USER_2[5]);
    }

    public static Users getWrongUser() {
        return new Users(MAIL_USER_WRONG_PSW[0],
                MAIL_USER_WRONG_PSW[1],
                MAIL_USER_WRONG_PSW[2],
                MAIL_USER_WRONG_PSW[3],
                MAIL_USER_WRONG_PSW[4],
                MAIL_USER_WRONG_PSW[5]);
    }

    public static String getEmail(Users user) {
        return user.getUsername() + user.getDomain();
    }


}
