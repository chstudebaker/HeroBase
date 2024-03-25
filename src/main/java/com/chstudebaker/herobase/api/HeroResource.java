package com.chstudebaker.herobase.api;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.*;
import java.util.List;

@Path("/heroes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroResource {

    // Inject your DAO layer here
    private final HeroDao heroDao;

    public HeroResource() {
        this.heroDao = new HeroDao();
    }

    @GET
    public List<Hero> getAllHeroes() {
        return heroDao.getAllHeroes();
    }

    @GET
    @Path("/{heroId}")
    public Hero getHeroById(@PathParam("heroId") int heroId) {
        return heroDao.getById(heroId);
    }

    @POST
    public Hero addHero(Hero hero) {
        int heroId = heroDao.insert(hero);
        return heroDao.getById(heroId); // Return the inserted hero
    }

    @PUT
    @Path("/{heroId}")
    public Hero updateHero(@PathParam("heroId") int heroId, Hero hero) {
        hero.setHeroId(heroId); // Set the ID of the hero to update
        if (heroDao.update(hero)) {
            return hero;
        } else {
            // Handle update failure
            return null;
        }
    }

    @DELETE
    @Path("/{heroId}")
    public void deleteHero(@PathParam("heroId") int heroId) {
        Hero hero = heroDao.getById(heroId);
        if (hero != null) {
            heroDao.delete(hero);
        } else {
            // Handle case where hero with given ID is not found
        }
    }
}
