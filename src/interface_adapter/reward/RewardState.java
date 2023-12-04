package interface_adapter.reward;

import entity.Badge;
import interface_adapter.logged_in.LoggedInState;

import java.util.ArrayList;
import java.util.List;

public class RewardState {
    private final List<Badge> EMPTY_LIST = new ArrayList<>();
    private String username = "";
    private String useCase = "";
    private String error = null;
    private List<Badge> rewards = EMPTY_LIST;

    public RewardState(RewardState copy) {
        username = copy.getUsername();
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public RewardState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Getter method for the use case
     *
     * @return the use case of the state
     */
    public String getUseCase() {
        return useCase;
    }

    /**
     * Setter method for the use case
     *
     * @param useCase the use case of the state
     */

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    /**
     * This method is responsible to get an error message.
     * @return error message
     */
    public String getError() {
        return error;
    }

    /**
     * Set an error message from the interactor.
     * @param error error message
     */
    public void setError(String error) {
        this.error = error;
    }
    public void setRewards(List<Badge> rewards) {
        this.rewards = rewards;
    }
    public List<Badge> getRewards() {
        return rewards;
    }
}
