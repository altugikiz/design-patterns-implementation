public class Main {

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===\n");

        // First call - instance will be created with data
        System.out.println("Calling getInstance() for the first time:");
        Singleton singleton1 = Singleton.getInstance("Initial Data");
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton1 data: " + singleton1.getData());

        System.out.println();

        // Second call - same instance will be returned (data parameter ignored)
        System.out.println("Calling getInstance() for the second time:");
        Singleton singleton2 = Singleton.getInstance("Different Data");
        System.out.println("singleton2 hash: " + singleton2.hashCode());
        System.out.println("singleton2 data: " + singleton2.getData());

        System.out.println();

        // Third call - still the same instance
        System.out.println("Calling getInstance() for the third time:");
        Singleton singleton3 = Singleton.getInstance("Another Data");
        System.out.println("singleton3 hash: " + singleton3.hashCode());
        System.out.println("singleton3 data: " + singleton3.getData());

        System.out.println();

        // Verify all references point to the same object
        System.out.println("=== Verification ===");
        System.out.println("singleton1 == singleton2: " + (singleton1 == singleton2));
        System.out.println("singleton2 == singleton3: " + (singleton2 == singleton3));
        System.out.println("All three references point to the same object!");
    }
}