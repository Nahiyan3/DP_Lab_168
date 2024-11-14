import java.util.ArrayList;
import java.util.List;

public class TwitterService implements SocialMediaService {
    @Override
    public List<Notification> fetchNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("1", "New follower on Twitter!", "Twitter"));
        return notifications;
    }

    @Override
    public void markAsRead(String notificationId) {
        System.out.println("Marked notification " + notificationId + " as read on Twitter");
    }

    @Override
    public void deleteNotification(String notificationId) {
        System.out.println("Deleted notification " + notificationId + " on Twitter");
    }
}
