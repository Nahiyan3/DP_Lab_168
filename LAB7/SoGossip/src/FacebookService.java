import java.util.ArrayList;
import java.util.List;

public class FacebookService implements SocialMediaService {
    @Override
    public List<Notification> fetchNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("2", "New friend request on Facebook!", "Facebook"));
        return notifications;
    }

    @Override
    public void markAsRead(String notificationId) {
        System.out.println("Marked notification " + notificationId + " as read on Facebook");
    }

    @Override
    public void deleteNotification(String notificationId) {
        System.out.println("Deleted notification " + notificationId + " on Facebook");
    }
}
