public class Trip {
    private String id;
    private String pickupLocation;
    private String dropOffLocation;
    private RideType rideType;
    private String status; // E.g., "Requested", "Driver Assigned", "Trip Started", "Completed", "Cancelled"
    private double fare;
    private double distance;
    private Driver driver;
    private Rider rider;
    private NotificationService notificationService;

    // Constructor
    public Trip(String id, RideType rideType, NotificationService notificationService) {
        this.id = id;
        this.rideType = rideType;
        this.status = "Requested";
        this.notificationService = notificationService;
    }

    // Set pickup location and automatically calculate distance
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
        this.distance = calculateDistance(); // Example calculation, could be more complex
    }

    // Set drop-off location and recalculate distance if needed
    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
        this.distance = calculateDistance(); // Example calculation, could be more complex
    }

    public String getPickupLocation() { return pickupLocation; }
    public String getDropOffLocation() { return dropOffLocation; }
    public String getStatus() { return status; }
    public double getFare() { return fare; }
    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDistance() { return distance; }
    public Driver getDriver() { return driver; }
    public Rider getRider() { return rider; }

    public String getId() {
        return id;
    }




    // Method to calculate fare based on distance and ride type
    public void calculateFare() {
        double baseFare = 5.0;
        double perMileRate = 2.0;

        switch (rideType) {
            case CARPOOL:
                perMileRate = 1.5;
                break;
            case LUXURY:
                baseFare = 10.0;
                perMileRate = 3.0;
                break;
            case BIKE:
                perMileRate = 1.0;
                break;
        }

        this.fare = baseFare + (perMileRate * distance);
        System.out.println("Calculated fare: " + fare);
    }

    // Method to assign a driver to the trip
    public void assignDriver(Driver driver) {
        if (driver != null) {
            this.driver = driver;
            this.status = "Driver Assigned";
            System.out.println("Driver assigned: " + driver.getName());
            notificationService.sendNotification("Driver " + driver.getName() + " has been assigned to your trip.", rider);
            notificationService.sendNotification("You have been assigned a trip with rider: " + rider.getName(), driver);
        } else {
            System.out.println("Invalid driver.");
        }
    }

    // Method to start the trip
    public void startTrip() {
        if (status.equals("Driver Assigned")) {
            this.status = "Trip Started";
            System.out.println("Trip started.");
            notificationService.sendNotification("Your trip has started.", rider);
            notificationService.sendNotification("Trip started with rider: " + rider.getName(), driver);
        } else {
            System.out.println("Trip cannot be started. Current status: " + status);
        }
    }

    // Method to complete the trip
    public void completeTrip() {
        if (status.equals("Trip Started")) {
            this.status = "Completed";
            System.out.println("Trip completed.");
            notificationService.sendNotification("Your trip has been completed. Fare: " + fare, rider);
            notificationService.sendNotification("Trip with rider " + rider.getName() + " has been completed. Fare: " + fare, driver);
        } else {
            System.out.println("Trip cannot be completed. Current status: " + status);
        }
    }

    // Method to handle payment processing
    public void processPayment(PaymentMethod paymentMethod) {
        if (paymentMethod != null) {
            paymentMethod.processPayment(fare);
            System.out.println("Payment processed. Fare: " + fare);
            notificationService.sendNotification("Payment processed successfully. Fare: " + fare, rider);
            notificationService.sendNotification("Payment for the trip has been processed. Fare: " + fare, driver);
        } else {
            System.out.println("Invalid payment method.");
        }
    }

    // Method to cancel the trip
    public void cancelTrip() {
        if (status.equals("Requested") || status.equals("Driver Assigned")) {
            this.status = "Cancelled";
            System.out.println("Trip cancelled.");
            notificationService.sendNotification("Your trip has been cancelled.", rider);
            notificationService.sendNotification("The trip has been cancelled.", driver);
        } else {
            System.out.println("Trip cannot be cancelled. Current status: " + status);
        }
    }

    // Method to update the trip status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Trip status updated to: " + status);
    }

    private double calculateDistance() {
        // This is a placeholder. You may replace it with actual distance calculation logic based on locations.
        return 10.0; // Assume a fixed distance for now.
    }
}
