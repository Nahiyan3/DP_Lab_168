public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message, User user) {
        // Implement SMS notification logic
        System.out.println("Sending SMS to " + user.getName() + ": " + message);
    }
}
