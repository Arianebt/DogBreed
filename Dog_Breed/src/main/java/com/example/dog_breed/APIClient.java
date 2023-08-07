package com.example.dog_breed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class APIClient {

    private static final String API_BASE_URL = "https://api.api-ninjas.com/v1/dogs?name=";
    private static final String API_KEY = "C6MbOVFQjhZ89rh1gPEXqQ==yfFlYCCTisGBtGsE";

    public String getBreedInfoByName(String breedName) throws IOException {
        String apiUrl = API_BASE_URL + breedName;
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = URI.create(apiUrl).toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.setRequestProperty("accept", "application/json");
            connection.setDoOutput(true);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStream responseStream = connection.getInputStream()) {
                    JsonArray jsonArray = JsonParser.parseReader(new BufferedReader(new InputStreamReader(responseStream)))
                            .getAsJsonArray();
                    if (!jsonArray.isJsonNull() && jsonArray.size() > 0) {
                        JsonElement breedObject = jsonArray.get(0);
                        JsonElement factElement = breedObject.getAsJsonObject().get("name");
                        if (factElement != null && !factElement.isJsonNull()) {
                            response.append(factElement.getAsString());
                        } else {
                            response.append("Fact not available for the breed: ").append(breedName);
                        }
                    } else {
                        response.append("Breed not found: ").append(breedName);
                    }
                }
            } else {
                throw new IOException("Failed to fetch data from the API. Response code: " + connection.getResponseCode());
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

}
