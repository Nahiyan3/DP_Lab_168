public class DigitalWalletPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing digital wallet payment of $" + amount);
    }
}
