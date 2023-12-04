package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.event.EventViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.reward.RewardViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.task.TaskViewModel;
import view.*;


public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("261");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        EventViewModel eventViewModel = new EventViewModel();
        TaskViewModel taskViewModel = new TaskViewModel();
        RewardViewModel rewardViewModel = new RewardViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./DATA/users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                signupViewModel, userDataAccessObject);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                signupViewModel, userDataAccessObject);

        views.add(loginView, loginView.viewName);
        views.add(signupView, signupView.viewName);

        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loggedInViewModel,
                loginViewModel, eventViewModel, taskViewModel, rewardViewModel);
      
        views.add(loggedInView, loggedInView.viewName);

        EventView eventView = EventUseCaseFactory.create(viewManagerModel, eventViewModel, loggedInViewModel);
        views.add(eventView, eventView.viewName);

        TaskView taskView = TaskUseCaseFactory.create(viewManagerModel, taskViewModel, loggedInViewModel);
        views.add(taskView, taskView.viewName);

//        RewardView rewardView = RewardUseCaseFactory.create(viewManagerModel, rewardViewModel, loggedInViewModel);
//        views.add(rewardView, rewardView.viewName);

        application.pack();
        application.setVisible(true);
    }
}
