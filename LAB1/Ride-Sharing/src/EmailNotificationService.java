public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message, User user) {
        System.out.println("Sending email to " + user.getName() + ": " + message);
    }
}
