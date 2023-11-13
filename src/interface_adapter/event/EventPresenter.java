package interface_adapter.event;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.event.EventOutputBoundary;
import use_case.event.EventOutputData;

public class EventPresenter implements EventOutputBoundary {
    private final EventViewModel eventViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public EventPresenter(EventViewModel eventViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.eventViewModel = eventViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EventOutputData response) {
        if (response.isBack()) {
            this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
            return;
        }
    }

    @Override
    public void prepareFailView(String error) {

    }
}
