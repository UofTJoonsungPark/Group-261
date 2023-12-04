package entity;

public class BadgeFactory {
    public Badge create(String image) {
        return new Badge(image);
    }
}
