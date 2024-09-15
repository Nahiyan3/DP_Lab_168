public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message, User user) {
        System.out.println("Sending SMS to " + user.getName() + ": " + message);
    }
}
