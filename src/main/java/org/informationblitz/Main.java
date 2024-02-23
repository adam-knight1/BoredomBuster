package org.informationblitz;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static TriviaService triviaService = new TriviaService();
    static WeatherService weatherService = new WeatherService();
    static FactService factService = new FactService();
    static DogService dogService = new DogService();
    static Scanner scanner = new Scanner(System.in);

    private static String[] symbols = null; // to store fetched symbols

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Welcome to the BoredomBuster app!");
            System.out.println("1. Get a Random Fact");
            System.out.println("2. Get Weather Information by Zip Code");
            System.out.println("3. Get a Trivia Question");
            System.out.println("4. Search information of Dog Breeds");
            System.out.println("5. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    factService.getFactsFromAPI();
                    break;
                case "2":
                    weatherService.getWeatherFromAPI();
                    break;
                case "3":
                    triviaService.getTriviaFromAPI();
                    break;
                case "4":
                    dogService.getDogInfoFromAPI();
                    break;
                case "5":
                    System.out.println("Exiting...");
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }
        }
    }
}








