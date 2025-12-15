public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // Keep going: Turn on the light
        light.turnOn();
    }

    @Override
    public void undo() {
        // Go back: What is the opposite of turning on the light? Turning it off.
        // I (the programmer) am writing this here. The system does not predict it.
        light.turnOff();
    }
}