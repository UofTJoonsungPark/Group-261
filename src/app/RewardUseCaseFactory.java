package app;
import data_access.FileRewardUserDataAccessObject;
import entity.Badge;
import entity.BadgeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.reward.*;
import use_case.login.LoginUserDataAccessInterface;
import use_case.reward.RewardDataAccessInterface;
import use_case.reward.RewardInputBoundary;
import use_case.reward.RewardInteractor;
import use_case.reward.RewardOutputBoundary;
import view.RewardView;

import java.io.IOException;


public class RewardUseCaseFactory {
    private RewardUseCaseFactory() {}
    public static RewardView create(
            ViewManagerModel viewManagerModel,
            RewardViewModel rewardViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LoggedInState loggedInState
    ) throws IOException {
        RewardController rewardController = createRewardUseCase(viewManagerModel, rewardViewModel, loggedInViewModel);
        return new RewardView(rewardViewModel, rewardController, userDataAccessObject, loggedInState);
    }

    private static RewardController createRewardUseCase(
            ViewManagerModel viewManagerModel,
            RewardViewModel rewardViewModel,
            LoggedInViewModel loggedInViewModel
    ) {
        RewardOutputBoundary rewardOutputBoundary = new RewardPresenter(rewardViewModel, loggedInViewModel, viewManagerModel);
        BadgeFactory badgeFactory = new BadgeFactory();
        RewardDataAccessInterface rewardDataAccessObject = new FileRewardUserDataAccessObject(badgeFactory);

        RewardInputBoundary rewardInteractor = new RewardInteractor(rewardOutputBoundary, badgeFactory, rewardDataAccessObject);
        return new RewardController(rewardInteractor, (RewardPresenter) rewardOutputBoundary);

    }
}
