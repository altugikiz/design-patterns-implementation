public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee); // Pass the inner coffee to the parent class
    }

    @Override
    public double getCost() {
        return super.getCost() + 15.0; // Coffee price + 15 TL for Milk
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk"; // Coffee name + ", Milk"
    }
}