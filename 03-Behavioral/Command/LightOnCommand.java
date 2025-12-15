public class LightOnCommand implements Command {
    private Light light; // You should know who to control.

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn(); // Transfer the transaction to the actual recipient
    }
}