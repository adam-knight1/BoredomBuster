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
        DogDTO dog = mapper.readValue(responseStream, DogDTO.class);
        //JsonNode root = mapper.readTree(responseStream);
        //System.out.println(root.path("fact").asText());

        if (dog != null) {
            System.out.println("The " + dogBreed + "'s energy level is: " + dog.getEnergy() );
            System.out.println("The " + dogBreed + "'s shedding level is: " + dog.getShedding() );
            System.out.println("The " + dogBreed + "'s trainability level is: " + dog.getTrainability() );
            System.out.println("The " + dogBreed + "'s minimum life expectancy is: " + dog.getMinLifeExpectancy() );
        }   System.out.println("The " + dogBreed + "'s maximum life expectancy is: " + dog.getMaxLifeExpectancy() );
    }

}
