package use_case.reward;


public interface RewardOutputBoundary {
    void prepareSuccessView(RewardOutputData response);
    void prepareFailView(String error);
}
