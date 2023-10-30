package use_case.login;

public class LoginOutputData {

    private final String username;
    private boolean join;

    public LoginOutputData(String username, boolean join) {
        this.username = username;
        this.join = join;
    }

    public String getUsername() {
        return username;
    }

    public boolean isJoin() {
        return join;
    }
}
