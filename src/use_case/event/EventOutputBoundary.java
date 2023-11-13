package use_case.event;

public interface EventOutputBoundary {
    void prepareSuccessView(EventOutputData response);
    void prepareFailView(String error);
}
