// We make it abstract because it won't be used on its own, Milk or Sugar will extend this.
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee tempCoffee; // The wrapped coffee object

    public CoffeeDecorator(Coffee coffee) {
        this.tempCoffee = coffee;
    }

    @Override
    public double getCost() {
        return tempCoffee.getCost(); // Ask the actual coffee for the cost
    }

    @Override
    public String getDescription() {
        return tempCoffee.getDescription(); // Ask the actual coffee for the description
    }
}