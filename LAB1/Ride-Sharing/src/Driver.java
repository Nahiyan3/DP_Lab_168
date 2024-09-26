public class Driver extends User {
    private String vehicleType;
    private String location;
    private boolean availability;
    private NotificationService notificationService;

    // Constructor
    public Driver(String id, String name, String vehicleType, String location, double rating, boolean availability, NotificationService notificationService) {
        super(id, name, location, rating);
        this.vehicleType = vehicleType;
        this.location = location;
        this.availability = availability;
        this.notificationService = notificationService;
    }

    // Getters and Setters
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isAvailable() { return availability; }
    public void setAvailable(boolean availability) { this.availability = availability; }

    // Method to accept a ride
    public void acceptRide(Trip trip) {
        if (availability) {
            this.availability = false; // Driver is no longer available
            trip.assignDriver(this); // Assign this driver to the trip
            System.out.println("Driver accepted the ride.");
        } else {
            System.out.println("Driver is not available.");
        }
    }

    // Method to rate a rider
    public void rateRider(Rider rider, double rating) {
        if (rider != null && rating >= 1 && rating <= 5) {
            rider.rateUser(rating);
            System.out.println("Rated rider " + rider.getName() + " with rating " + rating);

            // Notify the rider about the rating
            notificationService.sendNotification("You have been rated with a rating of " + rating, rider);
        } else {
            System.out.println("Invalid rider or rating.");
        }
    }

    // Method to update the driver's location
    public void updateLocation(String newLocation) {
        this.location = newLocation;
        System.out.println("Driver's location updated to: " + newLocation);
    }

    // Method to start a trip
    public void startTrip(Trip trip) {
        if (trip != null && trip.getStatus().equals("Driver Assigned")) {
            trip.setStatus("Trip Started");
            System.out.println("Trip started. Pickup location: " + trip.getPickupLocation());

            // Notify both rider and driver about the trip start
            notificationService.sendNotification("Your trip has started. Pickup location: " + trip.getPickupLocation(), trip.getRider());
            notificationService.sendNotification("Driver has started the trip. Pickup location: " + trip.getPickupLocation(), this);
        } else {
            System.out.println("Trip cannot be started. Either it's not assigned or invalid.");
        }
    }

    @Override
    public void rateUser(double rating) {
        // Implementation for rating the driver
        setRating(rating);
        System.out.println("Driver rated with rating: " + rating);
    }
}
