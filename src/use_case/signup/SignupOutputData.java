package use_case.signup;

public class SignupOutputData {
    private final String username;
    private String creationTime;
    private final boolean login;

    public SignupOutputData(String username, String creationTime, boolean login) {
        this.username = username;
        this.creationTime = creationTime;
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public boolean getLogin() {
        return login;
    }
}
