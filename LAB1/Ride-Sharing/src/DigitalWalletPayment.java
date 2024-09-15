public class DigitalWalletPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        // Implement digital wallet payment logic
        System.out.println("Processing digital wallet payment of $" + amount);
    }
}
