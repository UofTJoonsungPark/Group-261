package entity;

import java.time.LocalDateTime;

public class CommonUser implements User {
    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private int point;

    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     * @param point
     */
    CommonUser(String name, String password, LocalDateTime creationTime, int point) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.point = point;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public int getPoint() {return point;}
    public void setPoint(int point1) {point = point + point1;}
}
