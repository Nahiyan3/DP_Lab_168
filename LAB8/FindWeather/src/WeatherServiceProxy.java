import java.util.HashMap;
import java.util.Map;

public class WeatherServiceProxy implements WeatherService {

    private WeatherService openWeather;
    private WeatherService weatherStack;

    // Maps to store cached data and rate limiting timestamps
    private Map<String, String> weatherCache = new HashMap<>();
    private Map<String, Long> lastRequestTimes = new HashMap<>();

    // Cache expiration time (10 minutes in milliseconds)
    private static final long CACHE_EXPIRATION_TIME = 10 * 60 * 1000;

    // Rate limit cooldown (30 seconds in milliseconds)
    private static final long RATE_LIMIT_COOLDOWN = 30 * 1000;

    public WeatherServiceProxy(WeatherService openWeather, WeatherService weatherStack) {
        this.openWeather = openWeather;
        this.weatherStack = weatherStack;
    }

    @Override
    public String getWeatherByCityName(String cityName) throws Exception {
        long currentTime = System.currentTimeMillis();

        // Check if the data is cached and if it is still valid
        if (weatherCache.containsKey(cityName) && isCacheValid(cityName)) {
            System.out.println("Returning cached data...");
            return weatherCache.get(cityName);
        }

        // Check rate limiting for OpenWeather
        if (isRateLimitExceeded("openWeather", currentTime)) {
            System.out.println("Rate limit exceeded for OpenWeather, switching to WeatherStack...");
            return getWeatherFromWeatherStack(cityName, currentTime);
        }

        // Try to get data from OpenWeather
        try {
            String data = openWeather.getWeatherByCityName(cityName);
            updateCache(cityName, data);
            updateRequestTime("openWeather", currentTime);
            return data;
        } catch (Exception e) {
            // If OpenWeather fails, fallback to WeatherStack
            return getWeatherFromWeatherStack(cityName, currentTime);
        }
    }

    private boolean isCacheValid(String cityName) {
        // Check if cache is still valid (within the last 10 minutes)
        long timestamp = lastRequestTimes.get(cityName);
        return (System.currentTimeMillis() - timestamp) < CACHE_EXPIRATION_TIME;
    }

    private boolean isRateLimitExceeded(String provider, long currentTime) {
        // Check if the rate limit is exceeded for the specified provider
        return lastRequestTimes.containsKey(provider) && (currentTime - lastRequestTimes.get(provider)) < RATE_LIMIT_COOLDOWN;
    }

    private String getWeatherFromWeatherStack(String cityName, long currentTime) throws Exception {
        // Check rate limiting for WeatherStack
        if (isRateLimitExceeded("weatherStack", currentTime)) {
            throw new Exception("Rate limit exceeded for WeatherStack as well.");
        }

        // Fetch weather data from WeatherStack
        String data = weatherStack.getWeatherByCityName(cityName);
        updateCache(cityName, data);
        updateRequestTime("weatherStack", currentTime);
        return data;
    }

    private void updateCache(String cityName, String data) {
        weatherCache.put(cityName, data);
        lastRequestTimes.put(cityName, System.currentTimeMillis());
    }

    private void updateRequestTime(String provider, long currentTime) {
        lastRequestTimes.put(provider, currentTime);
    }
}
