public class Admin {
    private String id;
    private String name;
    private String role;

    public Admin(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void manageDriver(Driver driver, boolean activate) {
        // Activate or deactivate driver
        System.out.println((activate ? "Activated" : "Deactivated") + " driver: " + driver.getName());
    }

    public void manageRider(Rider rider, boolean activate) {
        // Activate or deactivate rider
        System.out.println((activate ? "Activated" : "Deactivated") + " rider: " + rider.getName());
    }

    public void viewTripHistory(Trip trip) {
        // View trip history
        System.out.println("Trip ID: " + trip.getId() + ", Status: " + trip.getStatus());
    }

    public void handleDispute(Trip trip, String resolution) {
        System.out.println("Handling dispute for trip ID: " + trip.getId() + ". Resolution: " + resolution);
    }

}
