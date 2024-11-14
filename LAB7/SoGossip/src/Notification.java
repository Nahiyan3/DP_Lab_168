public class Notification {
    String id;
    String message;
    String platform;
    boolean isRead;

    public Notification(String id, String message, String platform) {
        this.id = id;
        this.message = message;
        this.platform = platform;
        this.isRead = false; // default to unread
    }

    public void markAsRead() {
        isRead = true;
    }

    public void markAsUnread() {
        isRead = false;
    }
}
