package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for HeroDao
 */
class HeroDaoTest {

    private HeroDao heroDao;
    private PowersDao powersDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Run set up tasks before each test:
     * 1. Execute SQL which deletes everything from the table and inserts records.
     * 2. Create any objects needed in the tests.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        heroDao = new HeroDao();
        powersDao = new PowersDao();
    }

    /**
     * Verify successful retrieval of a Hero by ID
     */
    @Test
    void getByIdSuccess() {
        Hero retrievedHero = heroDao.getById(2);
        assertNotNull(retrievedHero);
        assertEquals("Falinex", retrievedHero.getCodeName());
        assertEquals("Paul Wyvernel", retrievedHero.getRealName());
    }

    /**
     * Verify successful insertion of a Hero
     */
    @Test
    void insertSuccess() {
        // Create an Hero with a Power
        Hero hero = new Hero("Crushstone", "Brock Pebble", "placeholder bio", "good", "test", "test", "test", "test", "test", "test");
        Powers power = new Powers("stone skin","test", hero);

        // Set Powers to Hero
        hero.setPowers(Collections.singletonList(power));

        // Insert the hero along with associated powers
        int insertedHeroId = heroDao.insert(hero);
        logger.debug("Inserted Hero ID: {}", insertedHeroId);

        // Retrieve the hero
        Hero retrievedHero = heroDao.getById(insertedHeroId);

        // Verify
        assertNotNull(retrievedHero);
        assertEquals(hero.getCodeName(), retrievedHero.getCodeName());
        assertEquals(hero.getRealName(), retrievedHero.getRealName());
        assertEquals(1, retrievedHero.getPowers().size());
        assertEquals(power.getDescription(), retrievedHero.getPowers().get(0).getDescription());

        // Add debug statements
        logger.debug("Retrieved Hero: {}", retrievedHero);
        logger.debug("Retrieved Powers: {}", retrievedHero.getPowers().stream().map(Powers::getDescription).collect(Collectors.toList()));
    }

    /**
     * Verify successful update of a Hero
     */
    @Test
    void updateSuccess() {
        String realName = "Exa Hershel";
        Hero heroToUpdate = heroDao.getById(8);
        heroToUpdate.setRealName(realName);
        heroDao.update(heroToUpdate);
        Hero heroAfterUpdate = heroDao.getById(8);
        assertEquals(realName, heroAfterUpdate.getRealName());
    }

    /**
     * Verify successful deletion of a Hero
     */
    @Test
    void deleteSuccess() {
        heroDao.delete(heroDao.getById(3));
        assertNull(heroDao.getById(3));
    }
    /**
     * Verify successful retrieval of all heroes
     */
    @Test
    void getAllHeroesSuccess() {
        List<Hero> heroes = heroDao.getAllHeroes();
        assertNotNull(heroes);
        assertFalse(heroes.isEmpty());
        assertEquals(20, heroes.size()); // Assuming there are 20 heroes in the test data
    }

    /**
     * Verify successful search for heroes by code name
     */
    @Test
    void searchHeroesByCodeNameSuccess() {
        List<Hero> heroes = heroDao.searchHeroes("codeName", "Falinex");
        assertNotNull(heroes);
        assertFalse(heroes.isEmpty());
        assertEquals(1, heroes.size());
        assertEquals("Falinex", heroes.get(0).getCodeName());
    }
}
