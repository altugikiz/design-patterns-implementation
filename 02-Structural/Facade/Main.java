public class Main {
    public static void main(String[] args) {
        System.out.println("=== Facade Pattern Demo ===\n");

        // 1. Create subsystem components (In real life, these come via Dependency
        // Injection)
        Amplifier amp = new Amplifier();
        Projector projector = new Projector();
        TheaterLights lights = new TheaterLights();

        // 2. Create the Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, projector, lights);

        // 3. User only calls simple methods
        homeTheater.watchMovie("Inception");

        // Movie ended...
        homeTheater.endMovie();
    }
}