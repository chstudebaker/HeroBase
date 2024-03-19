package com.hero.api;

import entity.Hero;
import persistance.HeroDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/heroes")
public class HeroResource {

    private HeroDao heroDao = new HeroDao();

    @GET
    @Path("/{heroId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hero getHeroById(@PathParam("heroId") int heroId) {
        return heroDao.getById(heroId);
    }
}
