public class InAppNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message, User user) {
        System.out.println("Sending in-app notification to " + user.getName() + ": " + message);
    }
}
