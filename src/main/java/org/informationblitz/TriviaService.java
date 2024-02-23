package org.informationblitz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class TriviaService {
    public String triviaQuestion;
    public String[] categories = {"music", "mathematics", "geography", "sciencenature",
            "general", "entertainment", "toysgames", "peopleplaces"};
    Scanner scanner = new Scanner(System.in);

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
                connection.setRequestProperty("X-Api-Key", "iksW+ahtgKdZfdUHvWXGXA==Tv4PHnyj3CpuUHQP");
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





