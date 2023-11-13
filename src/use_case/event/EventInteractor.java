package use_case.event;

public class EventInteractor implements EventInputBoundary {
    final EventOutputBoundary eventPresenter;

    public EventInteractor(EventOutputBoundary eventPresenter) {
        this.eventPresenter = eventPresenter;
    }

    @Override
    public void execute(EventInputData eventInputdata) {
        if (eventInputdata.isBack()) {
            eventPresenter.prepareSuccessView(new EventOutputData(true));
            return;
        }

    }
}
