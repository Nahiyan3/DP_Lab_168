import java.util.Scanner;

public class Rider extends User {
    private PaymentMethod preferredPaymentMethod;
    private PaymentMethod paymentMethod;
    private NotificationService notificationService;

    // Constructor
    public Rider(String id, String name, String location, double rating, PaymentMethod preferredPaymentMethod, NotificationService notificationService) {
        super(id, name, location, rating);
        this.preferredPaymentMethod = preferredPaymentMethod;
        this.notificationService = notificationService;
    }

    // Method to request a ride
    public Trip requestRide(String pickupLocation, String dropOffLocation, RideType rideType) {
        Trip trip = new Trip("trip1", rideType, notificationService);
        trip.setPickupLocation(pickupLocation);
        trip.setDropOffLocation(dropOffLocation);
        trip.setRider(this);
        notificationService.sendNotification("Ride requested successfully from " + pickupLocation + " to " + dropOffLocation, this);
        return trip;
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

    // Method to request a ride with dynamic ride type selection
    public void requestRide(Trip trip) {
        if (trip == null) {
            System.out.println("Trip cannot be null.");
            return;
        }

        // Set the rider for the trip
        trip.setRider(this);

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

    public void selectPaymentMethod(int choice) {
        switch (choice) {
            case 1:
                this.paymentMethod = new CreditCardPayment();
                System.out.println("Selected payment method: Credit Card");
                break;
            case 2:
                this.paymentMethod = new DigitalWalletPayment();
                System.out.println("Selected payment method: Digital Wallet");
                break;
            case 3:
                this.paymentMethod = new PayPalPayment();
                System.out.println("Selected payment method: PayPal");
                break;
            default:
                System.out.println("Invalid choice. No payment method selected.");
                this.paymentMethod = null; // Reset payment method if invalid choice
        }
    }

    public void choosePaymentMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Digital Wallet");
        System.out.println("3. PayPal");

        int choice = scanner.nextInt();
        selectPaymentMethod(choice);
    }

    // Method to make payment using the selected payment method
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

        return new Driver("driver1", "John Doe", "car", "Sample Location", 4.5, true, notificationService);
    }

    @Override
    public void rateUser(double rating) {
        // Implementation for rating the rider if needed
        System.out.println("Rider rated with rating: " + rating);
    }
}
