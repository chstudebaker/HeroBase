package persistance;

import entity.Hero;
import entity.Powers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PowerDaoTest {

    PowersDao dao;
    HeroDao heroDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        heroDao = new HeroDao();
        dao = new PowersDao();
    }

    @Test
    void getByIdSuccess() {
        System.out.println("Starting getByIdSuccess test...");

        Hero hero = new Hero("Windchild", "Lance Talon", "bio", "Good");
        heroDao.insert(hero);

        Powers powers = new Powers("Windchild", hero);
        dao.insert(powers);

        // Check if heroDao and dao are not null
        assertNotNull(heroDao);
        assertNotNull(dao);

        // Retrieve powers by ID
        Powers retrievedPowers = dao.getById(powers.getPowerID());

        // Check if retrievedPowers is not null
        assertNotNull(retrievedPowers);

        // Perform assertions
        assertEquals("Windchild", retrievedPowers.getDescription());
        assertEquals("Windchild", retrievedPowers.getHero().getCodeName());
        assertEquals("Lance Talon", retrievedPowers.getHero().getRealName());

        System.out.println("Assertions passed!");

        System.out.println("Ending getByIdSuccess test...");
    }

    @Test
    void updateSuccess() {
        String heroCodeName = "John";
        String heroRealName = "Smith";
        String powerDescription = "Super Strength";

        // Create a new Hero
        Hero newHero = new Hero(heroCodeName, heroRealName, "bio", "Good");
        heroDao.insert(newHero);

        // Create a new Powers entity associated with the new Hero
        Powers newPower = new Powers(powerDescription, newHero);
        dao.insert(newPower);

        // Retrieve an existing Powers entity to update its associated Hero
        Powers powerToUpdate = dao.getById(1);

        // Associate the new Hero with the existing Powers entity
        powerToUpdate.setHero(newHero);

        // Update the associated Hero within the existing Powers entity
        dao.update(powerToUpdate);

        // Retrieve the updated Powers entity
        Powers powerAfterUpdate = dao.getById(1);

        // Perform assertions
        assertEquals(heroCodeName, powerAfterUpdate.getHero().getCodeName());
        assertEquals(heroRealName, powerAfterUpdate.getHero().getRealName());
        assertEquals(powerDescription, powerAfterUpdate.getDescription());
    }


    @Test
    void deleteSuccess() {
        System.out.println("Starting deleteSuccess test...");

        Hero hero = new Hero("TestHero", "TestRealName", "testBio", "testAlignment");
        heroDao.insert(hero);

        Powers powersToDelete = new Powers("TestDescription", hero);
        dao.insert(powersToDelete);

        dao.delete(powersToDelete);
        assertNull(dao.getById(powersToDelete.getPowerID()));

        System.out.println("Ending deleteSuccess test...");
    }

    @Test
    void getAllSuccess() {
        System.out.println("Starting getAllSuccess test...");

        Hero hero1 = new Hero("Hero1", "RealName1", "Bio1", "Alignment1");
        heroDao.insert(hero1);

        Powers powers1 = new Powers("Description1", hero1);
        dao.insert(powers1);

        Hero hero2 = new Hero("Hero2", "RealName2", "Bio2", "Alignment2");
        heroDao.insert(hero2);

        Powers powers2 = new Powers("Description2", hero2);
        dao.insert(powers2);

        List<Powers> powers = dao.getAllPowers();
        assertEquals(2, powers.size()); // Check for the inserted entries only

        System.out.println("Ending getAllSuccess test...");
    }
}
