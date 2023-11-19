package interface_adapter.event;

/**
 * The EventState class is observed in EventViewModel class
 */
public class EventState {
    private String useCase = null;

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
}
