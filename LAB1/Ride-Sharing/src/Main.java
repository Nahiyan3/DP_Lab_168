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

        // Rider requests a ride
        Trip trip = rider.requestRide("Uptown", "Downtown", RideType.CARPOOL);

        // Assign driver to the trip
        trip.assignDriver(driver);

        // Test the Trip functionalities
        System.out.println("Testing Trip Functionality:");
        trip.calculateFare();
        trip.startTrip(); // Start the trip
        trip.completeTrip(); // Complete the trip

        // Test the Rider functionalities
        System.out.println("\nTesting Rider Functionality:");
        rider.rateDriver(driver, 3.5);
        System.out.println("Rating after changing: " + driver.getRating());
        rider.choosePaymentMethod(); // Example: selecting credit card payment
        rider.makePayment(20.0);

        // Test the Driver functionalities
        System.out.println("\nTesting Driver Functionality:");
        driver.acceptRide(trip);
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
    }
}
