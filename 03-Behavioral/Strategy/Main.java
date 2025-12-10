public class Main {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Demo ===\n");

        ShoppingCart cart = new ShoppingCart();

        // SCENARIO 1: User selected Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "999"));
        cart.checkout(100);

        System.out.println("---------------------------------");

        // SCENARIO 2: User changed their mind, wants to pay with PayPal
        // We only change the strategy without modifying the rest of the code.
        cart.setPaymentStrategy(new PayPalPayment("altug@example.com"));
        cart.checkout(250);
    }
}