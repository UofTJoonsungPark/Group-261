package use_case.reward;

import entity.Badge;
import entity.BadgeFactory;
import interface_adapter.reward.RewardPresenter;


public class RewardInteractor implements RewardInputBoundary {

    private final RewardOutputBoundary rewardPresenter;
    private final BadgeFactory badgeFactory;
    private final RewardDataAccessInterface rewardDataAccessObject;

    public RewardInteractor(RewardOutputBoundary rewardPresenter, BadgeFactory badgeFactory,
                            RewardDataAccessInterface rewardDataAccessObject) {
        this.rewardPresenter = rewardPresenter;
        this.badgeFactory = badgeFactory;
        this.rewardDataAccessObject = rewardDataAccessObject;

    }

    @Override
    public void execute(RewardInputData rewardInputData) {

    }

    @Override
    public void initialize(String username) {

    }
}
