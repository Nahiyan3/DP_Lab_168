import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WeatherService openWeather = new OpenWeatherAdapter();
        WeatherService weatherStack = new WeatherStackAdapter();
        WeatherServiceProxy weatherServiceProxy = new WeatherServiceProxy(openWeather, weatherStack);
        LocationService locationService = new LocationService();
        WeatherFacade weatherFacade = new WeatherFacade(weatherServiceProxy, locationService);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select location method: 1. By IP, 2. By City, 3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 1) {
                    System.out.println("Getting weather data by IP...");
                    System.out.println(weatherFacade.getWeatherByIP());
                } else if (choice == 2) {
                    System.out.print("Enter city name: ");
                    String cityName = scanner.nextLine();
                    System.out.println(weatherFacade.getWeatherByCity(cityName));
                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
