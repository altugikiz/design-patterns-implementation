public abstract class BeverageMaker {

    // TEMPLATE METHOD
    // The skeleton of the algorithm is here.
    // It's 'final' so no one can change this order.
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Common Step 1: Everyone boils water the same way
    private void boilWater() {
        System.out.println("Boiling water...");
    }

    // Common Step 2: Everyone pours into cup the same way
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }

    // VARIABLE STEPS (Abstract)
    // Subclasses (Tea/Coffee) must implement these themselves
    abstract void brew(); // Brewing method

    abstract void addCondiments(); // Extra ingredients
}