public class MediaPlayer {
    private State state;

    public MediaPlayer() {
        // Initial state: Paused
        this.state = new PausedState();
    }

    // Method to dynamically change the state
    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void pressButton() {
        // Delegate the action to the current state!
        // MediaPlayer doesn't think "What should I do now?"
        state.pressButton(this);
    }
}