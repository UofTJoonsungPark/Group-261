package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name  the name of the User
     * @param password  the password of the User
     * @return a new CommonUser
     */

    @Override
    public User create(String name, String password, LocalDateTime ldt) {
        return new CommonUser(name, password, ldt);
    }
}
