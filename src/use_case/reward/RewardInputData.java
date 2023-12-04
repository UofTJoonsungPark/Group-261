package use_case.reward;
import entity.Badge;

public class RewardInputData {
    private Badge badge;
    private String useCase;
    public RewardInputData(String useCase) {
        this.useCase = useCase;
    }

    public String getUseCase() {
        return useCase;
    }

    public Badge getBadge() {return this.badge;}

    public void setBadge(Badge badge) {this.badge = badge;}

}
