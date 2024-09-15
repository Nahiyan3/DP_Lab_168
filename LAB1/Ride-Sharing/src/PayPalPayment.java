public class PayPalPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        // Implement PayPal payment logic
        System.out.println("Processing PayPal payment of $" + amount);
    }
}
