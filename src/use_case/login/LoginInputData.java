package use_case.login;

public class LoginInputData {

    final private String username;
    final private String password;
    final private boolean join;

    public LoginInputData(String username, String password, boolean join) {
        this.username = username;
        this.password = password;
        this.join = join;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public boolean getJoin() {
        return join;
    }
}
