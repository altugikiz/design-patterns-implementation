public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff(); // Go away
    }

    @Override
    public void undo() {
        light.turnOn(); // Come back (The opposite of closing is opening)
    }
}