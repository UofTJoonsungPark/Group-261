package interface_adapter.reward;

public class RewardController {
    final RewardPresenter rewardPresenter;

    /**
     * Initialize a new RewardController.
     * @param rewardPresenter The presenter for the interactor.
     */
    public RewardController (RewardPresenter rewardPresenter) {
        this.rewardPresenter = rewardPresenter;
    }

    /**
     * Execute the given use case.
     *
     * @param useCase use case which does not interact with entities.
     */
    public void execute(String useCase, )
}
