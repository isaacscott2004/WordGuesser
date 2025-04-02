package gamePlay;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class WordFetcher {
    private static final String RANDOM_WORD_URL = "https://random-word-api.herokuapp.com/word";

    public static String getWord(){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(RANDOM_WORD_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                Gson gson = new Gson();
                JsonArray jsonArray = gson.fromJson(response.toString(), JsonArray.class);
                Random random = new Random();
                int size = jsonArray.size();
                int randint = random.nextInt(size);
                int count = 0;
                while(count <= 100000){
                   String potentialWord =  jsonArray.get(randint).getAsString();
                   if(potentialWord.length() >= 4 && potentialWord.length() <= 10){
                       return potentialWord;

                   }
                   count++;
                }
                return null;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

}
