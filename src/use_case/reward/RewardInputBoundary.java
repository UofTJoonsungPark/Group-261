package use_case.reward;

public interface RewardInputBoundary {
    void execute (RewardInputData rewardInputData);
    void initialize (String username);
}
