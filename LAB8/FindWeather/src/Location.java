public class Location {
    public String cityName;
    public double latitude;
    public double longitude;

    public Location(String cityName, double latitude, double longitude) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location: " + cityName + " (Lat: " + latitude + ", Long: " + longitude + ")";
    }
}
