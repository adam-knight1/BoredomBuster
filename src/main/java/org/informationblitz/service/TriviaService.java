package org.informationblitz.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.informationblitz.dto.DogDTO;
import org.informationblitz.dto.TriviaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;


@Service
public class TriviaService {
    public String triviaQuestion;
    public String[] categories = {"music", "mathematics", "geography", "sciencenature",
            "general", "entertainment", "toysgames", "peopleplaces"};
    private RestTemplate restTemplate = new RestTemplate();
    Scanner scanner = new Scanner(System.in);
    String apiUrl = "https://api.api-ninjas.com/v1/trivia?category=";


    public TriviaDTO getTrivia (String category) throws IOException {
        //get category from controller
        //append category to URL
        //open connection and set key
        //return TriviaDTO with random trivia question

        String apiKey = System.getenv("API_KEY");


        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("API key for trivia is not set");
        }

        String encodedCategory = category != null && !category.isEmpty() ? URLEncoder.encode(category, StandardCharsets.UTF_8.toString()) : "";
        String triviaApiUrl = "https://api.api-ninjas.com/v1/trivia" + (!encodedCategory.isEmpty() ? "?category=" + encodedCategory : "");

        URL url = new URL(triviaApiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("X-Api-key", apiKey);
        connection.setRequestProperty("accept", "application/json");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            TriviaDTO[] triviaArray = mapper.readValue(responseStream, TriviaDTO[].class);

            if (triviaArray.length > 0) {
                return triviaArray[0];
            } else {
                throw new IOException("No trivia found for entered category: " + category);
            }
        } else {
            throw new IOException("Response not 200 OK in getTrivia" + responseCode);
        }
    }

    public void getTriviaFromAPI() throws IOException {
        String catChoice = "";
        boolean isValidCategory = false;

        System.out.println("Please choose from the following available categories: ");
        System.out.println(Arrays.toString(categories));

        while (!isValidCategory) {

            catChoice = scanner.nextLine();
            isValidCategory = Arrays.asList(categories).contains(catChoice);

            if (!isValidCategory) {
                System.out.println(catChoice + " is an invalid selection.");
                System.out.println("Please choose from the following available categories: ");
                System.out.println(Arrays.toString(categories));
            }
        }
            System.out.println("You chose: " + catChoice);
            System.out.println("You'll be asked a series of 10 questions");
            System.out.println("At the end, you'll see your total score!");

        boolean keepGoing = true;
        int counter = 0;

        while (keepGoing) {
            try {
                URL url = new URL("https://api.api-ninjas.com/v1/trivia?category=" + catChoice);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("X-Api-Key", "x"); //encapsulating
                connection.setRequestProperty("accept", "application/json");
                InputStream responseStream = connection.getInputStream();
                int playerScore = 0;

                ObjectMapper mapper = new ObjectMapper();
                TriviaDTO[] trivia = mapper.readValue(responseStream, TriviaDTO[].class);

                if (trivia.length > 0) {
                    triviaQuestion = trivia[0].getQuestion();
                    System.out.println(triviaQuestion);
                    System.out.println("Enter your answer");
                    String userAnswer = scanner.nextLine();
                    String correctAnswer = trivia[0].getAnswer();

                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        counter++;
                        playerScore++;
                        System.out.println("Congratulations, you got it right!");
                        System.out.println(trivia[0].getAnswer());

                    } else {
                        counter++;
                        System.out.println("Oops, wrong answer!");
                        System.out.println(trivia[0].getAnswer());

                    }
                }
                if (counter > 10) {
                    keepGoing = false;
                    System.out.println("Game over!  Your score was: " + playerScore);
                }
            } catch (Exception e) {
                triviaQuestion = ("Failed to fetch trivia");
                e.printStackTrace();
            }
        }
    }
}





