public class WeatherData {
    public String cityName;
    public double latitude;
    public double longitude;
    public double temperature;
    public String weatherCondition;
    public String source;

    public WeatherData(String cityName, double latitude, double longitude, double temperature, String weatherCondition, String source) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.source = source;
    }

    @Override
    public String toString() {
        return String.format("Weather in %s from %s: %.1f°C, %s", cityName, source, temperature, weatherCondition);
    }
}
