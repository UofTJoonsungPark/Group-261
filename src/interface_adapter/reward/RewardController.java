package interface_adapter.reward;

import use_case.reward.RewardInputBoundary;

import java.time.LocalDate;

public class RewardController {
    private final RewardInputBoundary rewardUseCaseInteractor;
    final RewardPresenter rewardPresenter;

    /**
     * Initialize a new RewardController.
     *
     * @param rewardPresenter The presenter for the interactor.
     */
    public RewardController(RewardInputBoundary rewardUseCaseInteractor, RewardPresenter rewardPresenter) {
        this.rewardUseCaseInteractor = rewardUseCaseInteractor;
        this.rewardPresenter = rewardPresenter;
    }

    /**
     * Execute the given use case.
     *
     * @param useCase use case which does not interact with entities.
     */
    public void execute(String useCase, String image) {

    }

    public void execute(String useCase) {
        if ("back".equals(useCase)) {
            rewardPresenter.changeView();
        }
    }

    public void initialize(String username) {
        rewardUseCaseInteractor.initialize(username);
    }
}
