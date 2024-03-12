package persistance;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestServiceClient {

    @Test
    public void testHeroApiJSON() throws URISyntaxException {
        // Replace the URL with the base URL of your Hero API
        URI baseURI = new URI("http://localhost:8080/untitled_war/api");

        // Create a JAX-RS client
        Client client = ClientBuilder.newClient();

        // Build the target URL for the specific hero ID
        WebTarget target = client.target(baseURI).path("/heroes/1");

        // Verify that the client is targeting the expected URI
        assertTrue(target.getUri().equals(baseURI.resolve("/heroes/1")));

        // Adjust the expected response based on your API's response
        String expectedResponse = "{\"heroId\":1,\"codeName\":\"Windchild\",\"realName\":\"Lance Talon\",\"bio\":\"Placeholder bio for Lance Talon\",\"alignment\":\"Good\"}";

        // Make the request and compare the response
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals(expectedResponse, response);
    }
}
