public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0; // +5 TL for Sugar
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
}