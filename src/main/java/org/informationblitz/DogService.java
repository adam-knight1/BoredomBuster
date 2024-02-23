package org.informationblitz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DogService {
    Scanner scanner = new Scanner(System.in);

    public void getDogInfoFromAPI() throws IOException {
        System.out.println("Please enter the name of a dog breed that you want information on: ");
        String dogBreed = scanner.nextLine();

        System.out.println("You entered: " + dogBreed);

        URL url = new URL("https://api.api-ninjas.com/v1/dogs?name=" + dogBreed);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        //JsonNode root = mapper.readTree(responseStream);

        System.out.println(root.path("fact").asText());
    }

}
