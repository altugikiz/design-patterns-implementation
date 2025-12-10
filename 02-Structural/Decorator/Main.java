public class Main {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Demo (Starbucks) ===\n");

        // 1. Just Plain Coffee
        Coffee myCoffee = new SimpleCoffee();
        System.out.println("Order 1: " + myCoffee.getDescription() + " -> " + myCoffee.getCost() + " TL");

        // 2. Coffee with Milk (We wrap the plain coffee with milk)
        Coffee milkCoffee = new MilkDecorator(new SimpleCoffee());
        System.out.println("Order 2: " + milkCoffee.getDescription() + " -> " + milkCoffee.getCost() + " TL");

        // 3. Coffee with Milk and Sugar (Nested wrapping - like Matryoshka dolls)
        // new Sugar(new Milk(new Coffee()))
        Coffee superCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));

        System.out.println("Order 3: " + superCoffee.getDescription() + " -> " + superCoffee.getCost() + " TL");
    }
}