public class Main {
    public static void main(String[] args) {
        System.out.println("=== Template Method Pattern Demo ===\n");

        System.out.println("--- Preparing Tea ---");
        BeverageMaker myTea = new Tea();
        // We call a single method, the entire sequence runs behind the scenes
        myTea.prepareRecipe();

        System.out.println("\n--- Preparing Coffee ---");
        BeverageMaker myCoffee = new Coffee();
        myCoffee.prepareRecipe();
    }
}