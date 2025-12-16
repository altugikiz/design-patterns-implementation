public class Main {
    public static void main(String[] args) {
        System.out.println("=== State Pattern Demo (Media Player) ===\n");

        MediaPlayer player = new MediaPlayer();

        // 1. First press (Initially Paused -> Will become Playing)
        player.pressButton();

        // 2. Second press (Was Playing -> Will become Paused)
        player.pressButton();

        // 3. Third press (Was Paused -> Will become Playing)
        player.pressButton();
    }
}