package app;

import data_access.FileEventUserDataAccessObject;
import entity.EventFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.event.EventController;
import interface_adapter.event.EventPresenter;
import interface_adapter.event.EventViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.event.EventDataAccessInterface;
import use_case.event.EventInputBoundary;
import use_case.event.EventInteractor;
import use_case.event.EventOutputBoundary;
import view.EventView;

import java.util.HashMap;


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
        EventFactory eventFactory = new EventFactory();
        EventDataAccessInterface eventDataAccessObject = new FileEventUserDataAccessObject(new HashMap<>(),
                new HashMap<>(), eventFactory);

        EventInputBoundary eventInteracotr = new EventInteractor(eventOutputBoundary, eventFactory, eventDataAccessObject);
        return new EventController(eventInteracotr, (EventPresenter) eventOutputBoundary);
    }
}
