public class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        // Implement credit card payment logic
        System.out.println("Processing credit card payment of $" + amount);
    }
}
