interface Vehicle {
    String getType();
}

// Concrete Products
class Car implements Vehicle {
    @Override
    public String getType() {
        return "Car";
    }
}

class Bike implements Vehicle {
    @Override
    public String getType() {
        return "Bike";
    }
}

class Truck implements Vehicle {
    @Override
    public String getType() {
        return "Truck";
    }
}

// Abstract Creator
abstract class VehicleFactory {
    // Factory method that subclasses must implement
    abstract Vehicle createVehicle();
}

// Concrete Creators
class CarFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        // The factory creates its own special product here and returns
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Bike();
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Truck();
    }
}
