package org.boredombuster.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.boredombuster.dto.DogDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
@Service
public class DogService {
    Scanner scanner = new Scanner(System.in);

    public DogDTO getDogInfoFromAPI(String breedName) throws IOException {
        String encodedBreedName = URLEncoder.encode(breedName, StandardCharsets.UTF_8.toString());

        URL url = new URL("https://api.api-ninjas.com/v1/dogs?name=" + encodedBreedName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String apiKey = System.getenv("API_KEY"); //sourcing key from env variables
        connection.setRequestProperty("X-Api-key",apiKey);
        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DogDTO[] dogs = mapper.readValue(responseStream, DogDTO[].class);

        if (dogs.length > 0) {
            return dogs[0]; // grabbing the first dog in the array
        } else {
            //will add logic for dog not found
            return null;
        }
    }
}


//maintaining a copy of my original API for future print statement recylcing etc.

    /*public void getDogInfoFromAPI() throws IOException {
        System.out.println("Please enter the name of a dog breed that you want information on: ");
        String dogBreed = scanner.nextLine();

        //Using Java's URLEncoder class to account for frequent spaces in dog breed names
        String encodedDogBreed = URLEncoder.encode(dogBreed, StandardCharsets.UTF_8.toString());
        System.out.println("You entered: " + dogBreed);

        URL url = new URL("https://api.api-ninjas.com/v1/dogs?name=" + encodedDogBreed);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Api-key", "x");
        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //including this feature due to numerous, irrelevant dog breed attributes
        DogDTO[] dogs = mapper.readValue(responseStream, DogDTO[].class);

        if (dogs.length > 0) {
            DogDTO dog = dogs[0];
            System.out.println("The " + dogBreed + "'s energy level is: " + dog.getEnergy());
            System.out.println("The " + dogBreed + "'s shedding level is: " + dog.getShedding());
            System.out.println("The " + dogBreed + "'s trainability level is: " + dog.getTrainability());
            System.out.println("The " + dogBreed + "'s minimum life expectancy is: " + dog.getMinLifeExpectancy());
            System.out.println("The " + dogBreed + "'s maximum life expectancy is: " + dog.getMaxLifeExpectancy());
        } else {
            System.out.println("There was an error retrieving information for this dog breed");
        }
    }*/


