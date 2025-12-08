public class Singleton {

    // STEP 1: We hold a static variable of our own type inside the class.
    // Initially null (empty), because no one has requested it yet.
    private static Singleton instance;
    private String data;

    // STEP 2: We make the Constructor PRIVATE.
    // This is VERY IMPORTANT. If you don't do this, other classes can call "new Singleton()".
    // By doing this, we take control.
    private Singleton(String data) {
        this.data = data;
        System.out.println("Singleton instance is being created for the first time...");
    }

    // STEP 3: The only gateway to the outside world (Global Access Point).
    // It must be "static" because it will be called without creating an object.
    public static Singleton getInstance(String data) {
        // If it hasn't been created before, create it.
        synchronized(Singleton.class) {
            if (instance == null) {
                instance = new Singleton(data);
            }
        }
        // If it already exists, return the existing one.
        return instance;
    }

    public String getData() {
        return data;
    }
}