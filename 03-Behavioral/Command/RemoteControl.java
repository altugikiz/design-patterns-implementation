public class RemoteControl {
    private Command lastCommand; // We store the last operation in memory

    public void setCommand(Command command) {
        this.lastCommand = command; // Store the command in memory
    }

    // Main action button
    public void pressButton() {
        if (lastCommand != null) {
            System.out.println("[Button Pressed]");
            lastCommand.execute();
        }
    }

    // NEW: Undo button
    public void pressUndo() {
        if (lastCommand != null) {
            System.out.println("[Undo Button Pressed]");
            lastCommand.undo();
        } else {
            System.out.println("No operation to undo.");
        }
    }
}