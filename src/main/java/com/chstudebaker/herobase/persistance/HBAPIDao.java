package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Hero;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
public class HBAPIDao {
    Hero getHero() {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://localhost:8080/api/herobase/1");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Hero hero = null;
        try {
            hero = mapper.readValue(response, Hero.class);
        } catch (JsonProcessingException e) {

        }

        return hero;
    }

}
