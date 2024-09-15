public abstract class User {
    private String id;
    private String name;
    private String location;
    private double rating;

    // Constructor
    public User(String id, String name, String location, double rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // Method to update location
    public void updateLocation(String newLocation) {
        this.location = newLocation;
    }

    // Abstract method for rating the user
    public abstract void rateUser(double rating);
}

