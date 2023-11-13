package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.event.EventController;
import interface_adapter.event.EventPresenter;
import interface_adapter.event.EventViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.event.EventInputBoundary;
import use_case.event.EventInteractor;
import use_case.event.EventOutputBoundary;
import view.EventView;

public class EventUseCaseFactory {
    private EventUseCaseFactory() {}

    public static EventView create(
            ViewManagerModel viewManagerModel,
            EventViewModel eventViewModel,
            LoggedInViewModel loggedInViewModel
    ) {
        EventController eventController = createEventUseCase(viewManagerModel, eventViewModel, loggedInViewModel);
        return new EventView(eventViewModel, eventController);
    }

    private static EventController createEventUseCase(
            ViewManagerModel viewManagerModel,
            EventViewModel eventViewModel,
            LoggedInViewModel loggedInViewModel
    ) {
        EventOutputBoundary eventOutputBoundary = new EventPresenter(
                eventViewModel, loggedInViewModel, viewManagerModel);

        EventInputBoundary eventInteracotr = new EventInteractor(eventOutputBoundary);
        return new EventController(eventInteracotr);
    }
}
