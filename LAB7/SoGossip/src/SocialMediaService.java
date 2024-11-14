import java.util.List;

public interface SocialMediaService {
    List<Notification> fetchNotifications();
    void markAsRead(String notificationId);
    void deleteNotification(String notificationId);
}
