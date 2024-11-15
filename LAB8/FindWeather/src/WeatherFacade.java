public class WeatherFacade {

    private WeatherServiceProxy weatherServiceProxy;
    private LocationService locationService;

    public WeatherFacade(WeatherServiceProxy weatherServiceProxy, LocationService locationService) {
        this.weatherServiceProxy = weatherServiceProxy;
        this.locationService = locationService;
    }

    public String getWeatherByIP() {
        try {
            Location location = locationService.getLocationByIP();
            return weatherServiceProxy.getWeatherByCityName(location.cityName);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getWeatherByCity(String cityName) {
        try {
            return weatherServiceProxy.getWeatherByCityName(cityName);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
