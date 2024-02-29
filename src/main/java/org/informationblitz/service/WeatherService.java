package org.informationblitz.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.informationblitz.dto.WeatherDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class WeatherService {

    private final String apiKey = System.getenv("API_NINJAS_KEY");

    public WeatherDTO getWeatherFromAPI(String type, String value, String state) throws IOException {
        String apiUrl = "https://api.api-ninjas.com/v1/weather?";
        String queryParam = "";

        if ("zip".equals(type)) {
            queryParam = "zip=" + URLEncoder.encode(value, StandardCharsets.UTF_8);
        } else if ("city".equals(type)) {
            queryParam = "city=" + URLEncoder.encode(value, StandardCharsets.UTF_8);
            if (state != null && !state.isEmpty()) {
                queryParam += "&state=" + URLEncoder.encode(state, StandardCharsets.UTF_8);
            }
        }

        String urlString = apiUrl + queryParam;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", apiKey);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream responseStream = connection.getInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseStream, WeatherDTO.class);
            }
        } else {
            // Handle non-200 responses accordingly
            throw new IOException("Received non-OK response from external weather API: " + responseCode);
        }
    }
}



