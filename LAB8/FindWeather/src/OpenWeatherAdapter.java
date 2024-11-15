import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class OpenWeatherAdapter implements WeatherService {

    private String apiKey = "0ea9a19d44b46663756f1d0639ee628e";

    @Override
    public String getWeatherByCityName(String cityName) throws Exception {
        String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        String jsonResponse = response.toString();
        String temperature = parseValue(jsonResponse, "temp");

        return "Weather in " + cityName + " from OpenWeather: " + temperature + "°C";
    }

    // Helper method to parse the temperature from the JSON response
    private String parseValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) return "Unknown";

        // Move to the start of the value and extract
        startIndex += searchKey.length();
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) endIndex = json.indexOf("}", startIndex);
        return json.substring(startIndex, endIndex).trim();
    }
}
