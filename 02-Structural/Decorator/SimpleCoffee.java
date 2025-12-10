public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 50.0; // Plain coffee 50 TL
    }

    @Override
    public String getDescription() {
        return "Plain Coffee";
    }
}