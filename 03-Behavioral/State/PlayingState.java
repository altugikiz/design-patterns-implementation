public class PlayingState implements State {
    @Override
    public void pressButton(MediaPlayer context) {
        System.out.println("Button pressed: PAUSING the video.");
        // Change state: Now 'PausedState' is active
        context.setState(new PausedState());
    }
}