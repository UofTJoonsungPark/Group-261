package use_case.reward;

import entity.Badge;

import java.util.List;

public interface RewardDataAccessInterface {
    void writeMaps(String username);

    void saveBadge(Badge badge);

    void clearMaps();

    void deleteBadge(Badge badge);

    List<Badge> getBadges(Badge date);
}
