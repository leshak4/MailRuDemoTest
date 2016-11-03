package email.data;

public enum Users {

    MAIL_USER_1("mailrudemouser1", "demomailru7658_1", "@mail.ru"),
    MAIL_USER_WRONG_PSW("mailrudemouser1", "demomailru76589", "@mail.ru");

    private String username;

    private String password;

    private String domain;

    Users(final String username, final String password, final String domain) {
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

}
