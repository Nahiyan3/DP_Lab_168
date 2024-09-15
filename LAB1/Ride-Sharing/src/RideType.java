public enum RideType {
    CARPOOL("Carpool", 1.0),
    LUXURY("Luxury", 2.0),
    BIKE("Bike", 0.5);

    private final String typeName;
    private final double fareMultiplier;

    // Constructor
    RideType(String typeName, double fareMultiplier) {
        this.typeName = typeName;
        this.fareMultiplier = fareMultiplier;
    }

    // Getter and setters
    public String getTypeName() {
        return typeName;
    }

    public double getFareMultiplier() {
        return fareMultiplier;
    }

    // Convert enum to string representation
    @Override
    public String toString() {
        return typeName;
    }
}