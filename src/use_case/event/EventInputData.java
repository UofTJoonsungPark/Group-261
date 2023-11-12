package use_case.event;

public class EventInputData {
    private boolean isBack;
    public EventInputData(boolean isBack) {
        this.isBack = isBack;
    }

    public boolean isBack() {
        return isBack;
    }
}
