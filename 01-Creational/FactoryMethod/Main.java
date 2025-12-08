public class Main {

    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");

        // SCENARIO 1: Logistics application works with Car
        // Client (Main) only knows the Factory interface.
        VehicleFactory factory1 = new CarFactory();
        simulateDelivery(factory1);

        // SCENARIO 2: Logistics application started working with Truck
        // We only change the factory without modifying the rest of the code
        // (simulateDelivery method).
        VehicleFactory factory2 = new TruckFactory();
        simulateDelivery(factory2);

        // SCENARIO 3: Now we add a Bike courier
        VehicleFactory factory3 = new BikeFactory();
        simulateDelivery(factory3);
    }

    /**
     * Think of this method as "Game Scenario" or "Business Logic".
     * NOTE: You won't see 'if-else' or 'new Car()' inside this method.
     * It doesn't know which object will come, it only communicates with
     * VehicleFactory.
     */
    public static void simulateDelivery(VehicleFactory factory) {
        // 1. Object creation (Creation) - Delegated to factory
        Vehicle vehicle = factory.createVehicle();

        // 2. Object usage (Operation)
        System.out.println("Logistics Started: " + vehicle.getType() + " is on the way for delivery.");
        System.out.println("-----------------------------------------");
    }
}