package com.hero.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Hero;
import persistance.HeroDao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/heroes")
public class HeroResource {

    private HeroDao heroDao = new HeroDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/{heroId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHeroById(@PathParam("heroId") int heroId) {
        Hero hero = heroDao.getById(heroId);
        return convertHeroToJson(hero);
    }

    private String convertHeroToJson(Hero hero) {
        try {
            // Use Jackson's ObjectMapper to convert Hero object to JSON
            return objectMapper.writeValueAsString(hero);
        } catch (JsonProcessingException e) {
            // Handle exception (log or throw as needed)
            e.printStackTrace();
            return "{}"; // Return an empty JSON object in case of an error
        }
    }
}
