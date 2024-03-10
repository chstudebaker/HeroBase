package persistance;

import entity.Hero;
import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for HeroDao
 */
class HeroDaoTest {

    HeroDao heroDao;
    PowersDao powerDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Run set up tasks before each test:
     * 1. Execute SQL which deletes everything from the table and inserts records.
     * 2. Create any objects needed in the tests.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();

        heroDao = new HeroDao();
        powerDao = new PowersDao();
    }

    /**
     * Verify successful retrieval of an Hero
     */
    @Test
    void getByIdSuccess() {
        Hero retrievedHero = heroDao.getById(1);
        assertNotNull(retrievedHero);
        assertEquals("Windchild", retrievedHero.getCodeName());
        assertEquals("Lance Talon", retrievedHero.getRealName());
    }
    @Test
    void insertSuccess() {
        // Create an Hero with a Power
        Hero hero = new Hero("Crushstone", "Brock Pebble", "placeholder bio", "good");
        Powers power = new Powers("stone skin", hero);

        // Set Powers to Hero
        hero.setPowers(Collections.singletonList(power));

        // Insert the hero along with associated powers
        int insertedHeroId = heroDao.insert(hero);
        logger.debug("Inserted Hero ID: {}", insertedHeroId);

        // Retrieve the author
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
     * Verify successful update of an Hero
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
    @Test
    void deleteSuccess() {
        heroDao.delete(heroDao.getById(22));
        assertNull(heroDao.getById(22));
    }


}
