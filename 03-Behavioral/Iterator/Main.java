public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iterator Pattern Demo ===\n");

        NameRepository namesRepository = new NameRepository();

        // Request an "Iterator" from the collection
        Iterator iter = namesRepository.getIterator();

        // Continue as long as the iterator says "there is a next element"
        while (iter.hasNext()) {
            String name = (String) iter.next();
            System.out.println("Name: " + name);
        }
    }
}