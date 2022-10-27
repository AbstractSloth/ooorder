package be.sloth.ooorder.domain.customer;

public class Credentials {

    private final String eMail;

    private final String password;

    public Credentials(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }
}
