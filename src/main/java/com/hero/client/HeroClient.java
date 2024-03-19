package com.hero.client;

import com.hero.api.HeroResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HeroClient {
    private static final String BASE_URL = "http://localhost:8080/api/heroes/"; // Replace with your service URL

    public HeroResponse getHeroById(int heroId) {
        // Create a Jersey client
        Client client = ClientBuilder.newClient();

        // Call the service and map the response to HeroResponse
        Response response = client.target(BASE_URL + heroId)
                .request(MediaType.APPLICATION_JSON)
                .get();

        // Check if the request was successful (status code 200)
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // Map the response to HeroResponse
            HeroResponse heroResponse = response.readEntity(HeroResponse.class);
            // Close the client
            client.close();
            return heroResponse;
        } else {
            // Close the client
            client.close();
            // Return null if the request was not successful
            return null;
        }
    }
}
