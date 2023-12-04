package use_case.reward;

import entity.Badge;
import java.util.List;

public class RewardOutputData {
    private final List<Badge> rewards;
    public RewardOutputData(List<Badge> rewards) {
        this.rewards = rewards;
    }
    public List<Badge> getRewards() {
        return rewards;
    }

}
