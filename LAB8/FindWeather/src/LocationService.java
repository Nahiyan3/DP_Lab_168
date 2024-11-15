import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LocationService {
    private String ipStackKey = "75189d654492c4163af51a01fa5356db";
    private String realIp = "103.82.172.152";

    public Location getLocationByIP() throws Exception {
        String urlStr = "http://api.ipstack.com/" + realIp + "?access_key=" + ipStackKey;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Read the response
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Manually parse the JSON response
        String responseStr = response.toString();
        String cityName = parseValue(responseStr, "city");
        double latitude = Double.parseDouble(parseValue(responseStr, "latitude"));
        double longitude = Double.parseDouble(parseValue(responseStr, "longitude"));

        return new Location(cityName, latitude, longitude);
    }

    private String parseValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) return null;
        startIndex += searchKey.length();

        char startChar = json.charAt(startIndex);
        if (startChar == '\"') {
            int endIndex = json.indexOf("\"", startIndex + 1);
            return json.substring(startIndex + 1, endIndex);
        } else {
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) endIndex = json.indexOf("}", startIndex);  // Handle last item in JSON
            return json.substring(startIndex, endIndex).trim();
        }
    }
}
