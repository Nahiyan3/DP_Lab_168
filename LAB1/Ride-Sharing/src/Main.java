public class Main {
    public static void main(String[] args) {
        // Create instances of NotificationService implementations
        NotificationService emailNotification = new EmailNotificationService();
        NotificationService smsNotification = new SmsNotificationService();
        NotificationService inAppNotification = new InAppNotificationService();

        // Create instances of PaymentMethod implementations
        PaymentMethod creditCard = new CreditCardPayment();
        PaymentMethod digitalWallet = new DigitalWalletPayment();
        PaymentMethod paypal = new PayPalPayment();

        // Create instances of Driver and Rider
        Driver driver = new Driver("driver1", "John Doe", "Car", "Downtown", 4.5, true, emailNotification);
        Rider rider = new Rider("rider1", "Jane Smith", "Uptown", 4.0, digitalWallet, smsNotification);


        // Create a Trip and set rider and driver
        Trip trip = new Trip("trip1", "Uptown", "Downtown", RideType.CARPOOL, 10.0, inAppNotification);
        trip.setRider(rider);  // Initialize rider for the trip
        trip.setDriver(driver); // Initialize driver for the trip

        // Test the Trip functionalities
        System.out.println("Testing Trip Functionality:");
        trip.calculateFare();
        trip.assignDriver(driver);
        trip.processPayment(creditCard);
        trip.completeTrip();

        // Test the Rider functionalities
        System.out.println("\nTesting Rider Functionality:");
        rider.requestRide(trip);
        rider.rateDriver(driver, 5.0);
        System.out.println("Rating after changing" + rider.getRating());
        rider.makePayment(20.0);

        // Test the Driver functionalities
        System.out.println("\nTesting Driver Functionality:");
        driver.acceptRide();
        driver.updateLocation("Midtown");
        driver.startTrip(trip);
        driver.rateRider(rider, 4.5);

        // Test the Admin functionalities
        System.out.println("\nTesting Admin Functionality:");
        Admin admin = new Admin("admin1", "Alice Johnson", "System Admin");
        admin.manageDriver(driver, true);
        admin.manageRider(rider, true);
        admin.viewTripHistory(trip);
        admin.handleDispute(trip, "No issues reported.");
        admin.viewMetrics();
    }
}
