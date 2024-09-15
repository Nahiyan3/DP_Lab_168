public class Trip {
    private String id;
    private String pickupLocation;
    private String dropOffLocation;
    private RideType rideType;
    private String status; // E.g., "Requested", "Driver Assigned", "Trip Started", "Completed"
    private double fare;
    private double distance;
    private Driver driver;
    private Rider rider;
    private NotificationService notificationService;

    // Constructor
    public Trip(String id, String pickupLocation, String dropOffLocation, RideType rideType, double distance, NotificationService notificationService) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.rideType = rideType;
        this.status = "Requested";
        this.distance = distance;
        this.notificationService = notificationService;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropOffLocation() { return dropOffLocation; }
    public void setDropOffLocation(String dropOffLocation) { this.dropOffLocation = dropOffLocation; }

    public RideType getRideType() { return rideType; }
    public void setRideType(RideType rideType) { this.rideType = rideType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public Driver getDriver() { return driver; }
    public void setDriver(Driver driver) { this.driver = driver; }

    public Rider getRider() { return rider; }
    public void setRider(Rider rider) { this.rider = rider; }

    // Method to calculate fare based on distance and ride type
    public void calculateFare() {
        // Simplified fare calculation logic
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

            // Notify both rider and driver
            if (rider != null) {
                notificationService.sendNotification("Driver " + driver.getName() + " has been assigned to your trip.", rider);
            } else {
                System.out.println("Rider is not set.");
            }
            notificationService.sendNotification("You have been assigned a trip with rider: " + (rider != null ? rider.getName() : "Unknown"), driver);
        } else {
            System.out.println("Invalid driver.");
        }
    }

    // Method to complete the trip
    public void completeTrip() {
        if (status.equals("Trip Started")) {
            this.status = "Completed";
            System.out.println("Trip completed.");

            // Notify both rider and driver about the trip completion
            if (rider != null) {
                notificationService.sendNotification("Your trip has been completed. Fare: " + fare, rider);
            } else {
                System.out.println("Rider is not set.");
            }
            if (driver != null) {
                notificationService.sendNotification("Trip with rider " + (rider != null ? rider.getName() : "Unknown") + " has been completed. Fare: " + fare, driver);
            } else {
                System.out.println("Driver is not set.");
            }
        } else {
            System.out.println("Trip cannot be completed. Current status: " + status);
        }
    }

    // Method to handle payment (abstracted to PaymentMethod interface)
    public void processPayment(PaymentMethod paymentMethod) {
        if (paymentMethod != null) {
            paymentMethod.processPayment(fare);
            System.out.println("Payment processed. Fare: " + fare);
        } else {
            System.out.println("Invalid payment method.");
        }
    }
}
