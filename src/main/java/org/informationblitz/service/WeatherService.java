package org.informationblitz.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.informationblitz.dto.WeatherDTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {

    public WeatherDTO getWeatherFromAPI(String zip) throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/weather?zip=" + zip);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Api-key", "iksW+ahtgKdZfdUHvWXGXA==Tv4PHnyj3CpuUHQP");
        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter zip code:");
        String zip = scanner.nextLine();

        try {
            URL url = new URL("https://api.api-ninjas.com/v1/weather?zip=" + zip);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", "iksW+ahtgKdZfdUHvWXGXA==Tv4PHnyj3CpuUHQP");
            connection.setRequestProperty("accept", "application/json");

            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            String temperature = root.path("temp").asText();
            String windSpeed = root.path("wind_speed").asText();
            String windDegrees = root.path("wind_degrees").asText();
            String maxTemp = root.path("max_temp").asText();
            String minTemp = root.path("min_temp").asText();

            System.out.println("Temperature: " + temperature);
            System.out.println("Wind Speed: " + windSpeed);
            System.out.println("Wind Degrees: " + windDegrees);
            System.out.println("Max Temperature: " + maxTemp);
            System.out.println("Min Temperature: " + minTemp);
        } catch (Exception e) {
            System.out.println("Failed to fetch weather data.");
            e.printStackTrace();
        }*/
    }
}
