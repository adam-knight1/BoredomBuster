package org.informationblitz.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.informationblitz.dto.WeatherDTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeatherService {

    public WeatherDTO getWeatherFromAPI(String queryType, String queryValue, String state, String country) throws IOException {
        String apiKey = System.getenv("API_KEY"); //source apiKey from env variables
        StringBuilder requestUrlBuilder = new StringBuilder("https://api.api-ninjas.com/v1/weather?");
        //the sb is created and used to create the URL for each request depending on user input

        switch (queryType.toLowerCase()) {
            case "zip":
                requestUrlBuilder.append("zip=").append(URLEncoder.encode(queryValue, StandardCharsets.UTF_8.toString()));
                break;
            case "city":
                requestUrlBuilder.append("city=").append(URLEncoder.encode(queryValue, StandardCharsets.UTF_8.toString()));
                if (state != null && !state.isEmpty()) {
                    requestUrlBuilder.append("&state=").append(URLEncoder.encode(state, StandardCharsets.UTF_8.toString()));
                }
                if (country != null && !country.isEmpty()) {
                    requestUrlBuilder.append("&country=").append(URLEncoder.encode(country, StandardCharsets.UTF_8.toString()));
                }
                break;
            case "latlon":
                String[] coords = queryValue.split(",");
                if (coords.length == 2) {
                    requestUrlBuilder.append("lat=").append(URLEncoder.encode(coords[0], StandardCharsets.UTF_8.toString()))
                            .append("&lon=").append(URLEncoder.encode(coords[1], StandardCharsets.UTF_8.toString()));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid query type: " + queryType);
        }

        HttpURLConnection connection = null;
        try {
            URL url = new URL(requestUrlBuilder.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", apiKey);
            connection.setRequestProperty("accept", "application/json");

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }

            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            WeatherDTO weather = mapper.readValue(responseStream, WeatherDTO.class);
            return weather;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
