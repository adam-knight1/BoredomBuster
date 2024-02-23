package org.informationblitz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FactService {

    public void getFactsFromAPI() {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while (keepGoing)
        try {
            System.out.println("How many facts would you like to see, up to 3");
            int numOfFacts = scanner.nextInt();

            URL url = new URL("https://api.api-ninjas.com/v1/facts?limit="+numOfFacts);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-key", "iksW+ahtgKdZfdUHvWXGXA==Tv4PHnyj3CpuUHQP");
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            int factCounter = 1 ;

            if (root.isArray()) {
                for (JsonNode node : root) {
                    String fact = node.path("fact").asText();
                    System.out.println("Fact number: " + factCounter + ": " + fact);
                    factCounter++;
                    if (factCounter > numOfFacts) {
                        keepGoing = false;
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

