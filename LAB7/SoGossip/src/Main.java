import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NotificationAggregatorFacade aggregator = new NotificationAggregatorFacade();

        // Register different platform services
        aggregator.addService(new TwitterService());
        aggregator.addService(new FacebookService());
        // Add more services as needed (e.g., LinkedInService, InstagramService)

        // Fetch and display all notifications
        List<Notification> notifications = aggregator.getAllNotifications();
        for (Notification notification : notifications) {
            System.out.println(notification.platform + ": " + notification.message);
        }

        // Interact with notifications
        aggregator.markNotificationAsRead("Twitter", "1");
        aggregator.deleteNotification("Facebook", "2");

    }
}