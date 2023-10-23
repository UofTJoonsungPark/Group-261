package entity;

import java.util.ArrayList;
import java.util.List;

public class RewardSystem {
    private int totlaPoint;
    private final List<Badge> earned;

    private final List<Badge> badges;

    public RewardSystem(List<Badge> badges) {
        this.totlaPoint = 0;
        this.earned = new ArrayList<>();
        this.badges = badges;
    }
}
