public class Main {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern (With Undo Support) ===\n");

        Light livingRoomLight = new Light();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        // SCENARIO 1: Turn on the light and then undo
        System.out.println("--- Test 1: Turn On Operation ---");
        remote.setCommand(lightOn);
        remote.pressButton(); // Lights On
        remote.pressUndo(); // Lights Off (Reverse of turning on)

        System.out.println("\n--- Test 2: Turn Off Operation ---");
        // SCENARIO 2: Turn off the light and then undo
        remote.setCommand(lightOff);
        remote.pressButton(); // Lights Off
        remote.pressUndo(); // Lights On (Reverse of turning off)
    }
}