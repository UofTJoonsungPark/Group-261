//package app;
//import data_access.FileRewardUserDataAccessObject;
//import entity.Badge;
//import entity.BadgeFactory;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.logged_in.LoggedInViewModel;
//import interface_adapter.reward.*;
//import interface_adapter.signup.SignupController;
//import use_case.reward.RewardDataAccessInterface;
//import use_case.reward.RewardInputBoundary;
//import use_case.reward.RewardInteractor;
//import use_case.reward.RewardOutputBoundary;
//import view.RewardView;
//import view.SignupView;
//
//import javax.swing.*;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
//
//public class RewardUseCaseFactory {
//    private RewardUseCaseFactory() {}
//    public static RewardView create(
//            ViewManagerModel viewManagerModel,
//            RewardViewModel rewardViewModel,
//            LoggedInViewModel loggedInViewModel
//    ) {
//        RewardController rewardController = createRewardUseCase(viewManagerModel, rewardViewModel, loggedInViewModel);
//        return new RewardView(rewardViewModel, rewardController);
//    }
//
//    private static RewardController createRewardUseCase(
//            ViewManagerModel viewManagerModel,
//            RewardViewModel rewardViewModel,
//            LoggedInViewModel loggedInViewModel
//    ) {
//        RewardOutputBoundary rewardOutputBoundary = new RewardPresenter(rewardViewModel, loggedInViewModel, viewManagerModel);
//        BadgeFactory badgeFactory = new BadgeFactory();
//        RewardDataAccessInterface rewardDataAccessObject = new FileRewardUserDataAccessObject(new HashMap<>(), badgeFactory);
//
//        RewardInputBoundary rewardInteractor = new RewardInteractor(rewardOutputBoundary, badgeFactory, rewardDataAccessObject);
//        return new RewardController(rewardInteractor, (RewardPresenter) rewardOutputBoundary);
//
//    }
//}
