public class Coffee extends BeverageMaker {
    @Override
    void brew() {
        System.out.println("Dripping coffee through filter...");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding milk and sugar...");
    }
}