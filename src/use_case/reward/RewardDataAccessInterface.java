package use_case.reward;

import entity.Badge;

import java.util.ArrayList;


public interface RewardDataAccessInterface {
    ArrayList<Badge> getBadges(int point);
}
