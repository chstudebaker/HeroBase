package com.chstudebaker.herobase.api;

import com.chstudebaker.herobase.entity.Hero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHbapiClient {

    @Test
    public void testHbapiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://localhost:8080/api/heroes/1"); // Adjust the URL to match your API endpoint
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Hero hero = null;
        hero = mapper.readValue(response, Hero.class);

        assertEquals("Windchild", hero.getCodeName());
    }
}
