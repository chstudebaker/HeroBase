package com.chstudebaker.restservice;

import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.entity.Hero;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/heroes")
public class Herobase {

    // Initialize the DAO instance
    private HeroDao heroDao = new HeroDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHeroes() {
        // Logic to retrieve all heroes from the database
        List<Hero> allHeroes = heroDao.getAllHeroes();

        // Check if any heroes were found
        if (allHeroes.isEmpty()) {
            // If no heroes were found, return 404 Not Found status
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            // If heroes were found, return them as JSON
            GenericEntity<List<Hero>> entity = new GenericEntity<>(allHeroes) {};
            return Response.ok(entity).build();
        }
    }
    @GET
    @Path("/{heroId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeroById(@PathParam("heroId") int heroId) {
        // Logic to retrieve hero by ID from the database
        Hero hero = heroDao.getById(heroId);

        // Check if hero is found
        if (hero == null) {
            // If hero not found, return 404 Not Found status
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            // If hero found, return it as JSON
            return Response.ok(hero).build();
        }
    }
}

