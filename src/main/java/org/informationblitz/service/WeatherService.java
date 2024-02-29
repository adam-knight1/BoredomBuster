package org.informationblitz.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.informationblitz.controller.WeatherController;
import org.informationblitz.dto.WeatherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);


    public WeatherDTO getWeatherFromAPI(String type, String value, String state) throws IOException {
        System.out.println("getWeatherFromAPI called with type: " + type + ", value: " + value + ", state: " + state);
        logger.info(type);
        String apiKey = System.getenv("API_KEY"); //sourcing key from env variables


        String apiUrl = "https://api.api-ninjas.com/v1/weather?";
        StringBuilder queryParam = new StringBuilder();

        if ("zip".equals(type)) {
            queryParam.append("zip=").append(URLEncoder.encode(value, StandardCharsets.UTF_8));
        } else if ("city".equals(type)) {
            queryParam.append("city=").append(URLEncoder.encode(value, StandardCharsets.UTF_8));
            if (state != null && !state.isEmpty()) {
                queryParam.append("&state=").append(URLEncoder.encode(state, StandardCharsets.UTF_8));
            }
        }

        // Logging the constructed URL to verify it's correct
        String urlString = apiUrl + queryParam.toString();
        System.out.println("Requesting weather data from: " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", apiKey);

        int responseCode = connection.getResponseCode();
        // Logging the response code to see if the request was successful
        System.out.println("Response Code from API: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream responseStream = connection.getInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                WeatherDTO weatherDTO = mapper.readValue(responseStream, WeatherDTO.class);
                // Logging the deserialized object to ensure it's correctly populated
                System.out.println("WeatherDTO: " + weatherDTO);
                return weatherDTO;
            }
        } else {
            // logging the error stream from the connection if available
            try (InputStream errorStream = connection.getErrorStream()) {
                if (errorStream != null) {
                    String errorResponse = new BufferedReader(new InputStreamReader(errorStream))
                            .lines().collect(Collectors.joining("\n"));
                    System.out.println("Error response from API: " + errorResponse);
                }
            }
            throw new IOException("Received non-OK response from external weather API: " + responseCode);
        }
    }
}



