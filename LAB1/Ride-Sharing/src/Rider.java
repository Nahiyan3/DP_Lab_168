public class Rider extends User {
    private PaymentMethod preferredPaymentMethod;
    private NotificationService notificationService;

    // Constructor
    public Rider(String id, String name, String location, double rating, PaymentMethod preferredPaymentMethod, NotificationService notificationService) {
        super(id, name, location, rating);
        this.preferredPaymentMethod = preferredPaymentMethod;
        this.notificationService = notificationService;
    }

    // Getter and setters
    public PaymentMethod getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void setPreferredPaymentMethod(PaymentMethod preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    // Method to request a ride
    public void requestRide(Trip trip) {
        if (trip == null) {
            System.out.println("Trip cannot be null.");
            return;
        }

        // Find an available driver based on trip type and location
        Driver driver = findAvailableDriver(trip);

        if (driver != null) {
            // Assign the driver to the trip and calculate fare
            trip.assignDriver(driver);
            trip.calculateFare();

            // Notify both rider and driver about the ride request
            notificationService.sendNotification("Ride requested successfully. Estimated fare: " + trip.getFare(), this);
            notificationService.sendNotification("New ride request assigned to you. Pickup location: " + trip.getPickupLocation(), driver);

            System.out.println("Ride requested successfully. Estimated fare: " + trip.getFare());
        } else {
            System.out.println("No available drivers found.");
        }
    }

    // Method to rate the driver after a trip
    public void rateDriver(Driver driver, double rating) {
        if (driver != null && rating >= 1 && rating <= 5) {
            driver.rateUser(rating);
            System.out.println("Rated driver " + driver.getName() + " with rating " + rating);

            // Notify the driver about the rating
            notificationService.sendNotification("You have been rated with a rating of " + rating, driver);
        } else {
            System.out.println("Invalid driver or rating.");
        }
    }

    // Method to make payment
    public void makePayment(double amount) {
        if (preferredPaymentMethod != null) {
            preferredPaymentMethod.processPayment(amount);
            System.out.println("Payment of " + amount + " processed using " + preferredPaymentMethod.getClass().getSimpleName());
        } else {
            System.out.println("No preferred payment method set.");
        }
    }

    // Finding an available driver based on trip type and location
    private Driver findAvailableDriver(Trip trip) {
        // For simplicity, return a sample driver
        // Replace this with actual logic to find available drivers from a data source
        return new Driver("driver1", "John Doe", "car", "Sample Locations", 4.5, true, notificationService);
    }

    @Override
    public void rateUser(double rating) {
        // Implementation for rating the rider if needed
        System.out.println("Rider rated with rating: " + rating);
    }
}
