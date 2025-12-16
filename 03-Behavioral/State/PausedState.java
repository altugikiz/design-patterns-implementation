public class PausedState implements State {
    @Override
    public void pressButton(MediaPlayer context) {
        System.out.println("Button pressed: PLAYING the video.");
        // Change state: Now 'PlayingState' is active
        context.setState(new PlayingState());
    }
}