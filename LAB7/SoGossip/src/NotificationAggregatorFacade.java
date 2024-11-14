import java.util.ArrayList;
import java.util.List;

public class NotificationAggregatorFacade {
    private List<SocialMediaService> services = new ArrayList<>();

    public void addService(SocialMediaService service) {
        services.add(service);
    }

    public List<Notification> getAllNotifications() {
        List<Notification> allNotifications = new ArrayList<>();
        for (SocialMediaService service : services) {
            allNotifications.addAll(service.fetchNotifications());
        }
        return allNotifications;
    }


    public void markNotificationAsRead(String platform, String notificationId) {
        for (SocialMediaService service : services) {
            if (service instanceof TwitterService && platform.equals("Twitter")) {
                service.markAsRead(notificationId);
            } else if (service instanceof FacebookService && platform.equals("Facebook")) {
                service.markAsRead(notificationId);
            }
        }
    }

    public void deleteNotification(String platform, String notificationId) {
        for (SocialMediaService service : services) {
            if (service instanceof TwitterService && platform.equals("Twitter")) {
                service.deleteNotification(notificationId);
            } else if (service instanceof FacebookService && platform.equals("Facebook")) {
                service.deleteNotification(notificationId);
            }

        }
    }
}
