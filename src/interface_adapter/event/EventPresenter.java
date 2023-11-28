package interface_adapter.event;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.event.EventOutputBoundary;
import use_case.event.EventOutputData;

/**
 * The EventPresenter class is responsible for displaying the output data of the interactor on the View.
 */
public class EventPresenter implements EventOutputBoundary {
    private final EventViewModel eventViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor to initialize a EventPresenter instance
     *
     * @param eventViewModel    Event ViewModel
     * @param loggedInViewModel LoggedIn ViewModel
     * @param viewManagerModel  ViewManager Model
     */
    public EventPresenter(EventViewModel eventViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.eventViewModel = eventViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Request to present success view
     */
    @Override
    public void prepareSuccessView(EventOutputData response) {
        EventState eventState = eventViewModel.getState();
        eventState.setEvents(response.getEvents());
    }

    /**
     * Request to change the view
     */
    public void changeView() {
        EventState eventState = eventViewModel.getState();
        if (eventState.getUseCase().equals(EventViewModel.BACK_USE_CASE)) {
            this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }

    /**
     * Request to present fail view
     */
    @Override
    public void prepareFailView(String error) {
        EventState eventState = eventViewModel.getState();
        eventState.setError(error);
        this.eventViewModel.firePropertyChanged();
    }
}
