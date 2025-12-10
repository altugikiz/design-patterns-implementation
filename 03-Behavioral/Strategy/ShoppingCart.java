public class ShoppingCart {

    // Shopping cart holds the current payment method
    private PaymentStrategy paymentStrategy;

    // We can change the payment method at Runtime!
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method!");
            return;
        }
        // DELEGATION: Delegates the operation to the strategy object
        paymentStrategy.pay(amount);
    }
}