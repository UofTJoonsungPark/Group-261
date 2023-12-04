package interface_adapter.reward;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.reward.RewardOutputBoundary;
import use_case.reward.RewardOutputData;

public class RewardPresenter implements RewardOutputBoundary {
    private final RewardViewModel rewardViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor to initialize a EventPresenter instance
     *
     * @param rewardViewModel    Reward ViewModel
     * @param loggedInViewModel LoggedIn ViewModel
     * @param viewManagerModel  ViewManager Model
     */
    public RewardPresenter(RewardViewModel rewardViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.rewardViewModel = rewardViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Request to present success view
     */
    @Override
    public void prepareSuccessView(RewardOutputData response) {
        RewardState rewardState = rewardViewModel.getState();
        rewardState.setRewards(response.getRewards());
        rewardViewModel.firePropertyChanged();
    }

    /**
     * Request to change the view
     */
    public void changeView() {
        RewardState rewardState = rewardViewModel.getState();
        if (rewardState.getUseCase().equals(rewardViewModel.BACK_USE_CASE)) {
            this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }

    /**
     * Request to present fail view
     */
    @Override
    public void prepareFailView(String error) {
        RewardState rewardState = rewardViewModel.getState();
        rewardState.setError(error);
        this.rewardViewModel.firePropertyChanged();
    }
}
