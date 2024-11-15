import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherStackAdapter implements WeatherService {

    private String apiKey = "d8818039af5d6c4291cc862d106d0af6";  // Replace with your actual WeatherStack API Key

    @Override
    public String getWeatherByCityName(String cityName) throws Exception {
        String urlStr = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + cityName;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Parsing the response for the temperature value
        String jsonResponse = response.toString();
        String temperature = parseValue(jsonResponse, "temperature");

        return "Weather in " + cityName + " from WeatherStack: " + temperature + "°C";
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
