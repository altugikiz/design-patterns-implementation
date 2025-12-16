public class HomeTheaterFacade {
    // Facade knows and manages the subsystems
    private Amplifier amp;
    private Projector projector;
    private TheaterLights lights;

    public HomeTheaterFacade(Amplifier amp, Projector projector, TheaterLights lights) {
        this.amp = amp;
        this.projector = projector;
        this.lights = lights;
    }

    // ONE-TOUCH MOVIE WATCHING
    public void watchMovie(String movie) {
        System.out.println("--- Starting Movie Mode ---");
        lights.dim(10); // Dim the lights
        projector.on(); // Turn on the projector
        projector.setInput("HDMI"); // Set input
        amp.on(); // Turn on the amplifier
        amp.setVolume(5); // Set the volume level
        System.out.println("Movie starting: " + movie);
    }

    // ONE-TOUCH SHUTDOWN
    public void endMovie() {
        System.out.println("\n--- Shutting Down Theater System ---");
        lights.on();
        projector.off();
        amp.off();
    }
}