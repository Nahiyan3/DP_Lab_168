public enum RideType {
    CARPOOL("Carpool", 1.0), // Base fare multiplier for Carpool
    LUXURY("Luxury", 2.0),  // Base fare multiplier for Luxury
    BIKE("Bike", 0.5);      // Base fare multiplier for Bike

    private final String typeName;
    private final double fareMultiplier; // Multiplier to calculate fare

    // Constructor
    RideType(String typeName, double fareMultiplier) {
        this.typeName = typeName;
        this.fareMultiplier = fareMultiplier;
    }

    // Getter for the type name
    public String getTypeName() {
        return typeName;
    }

    // Getter for the fare multiplier
    public double getFareMultiplier() {
        return fareMultiplier;
    }

    // Convert enum to string representation
    @Override
    public String toString() {
        return typeName;
    }
}