public class Main {
    public static void main(String[] args) {
        // Sending Email notification
        Notification emailNotification = new EmailNotification();
        emailNotification.send("email@example.com", "Hello via Email!");

        // Sending SMS notification
        Notification smsNotification = new SMSNotification();
        smsNotification.send("1234567890", "Hello via SMS!");

        // Sending Postal Mail using the adapter
        PostalMail postalMail = new PostalMail();
        Notification postalMailNotification = new PostalMailAdapter(postalMail, "John Doe", "123 Main St, Springfield");
        postalMailNotification.send("ignored_receiver", "Hello via Postal Mail!");
    }
}