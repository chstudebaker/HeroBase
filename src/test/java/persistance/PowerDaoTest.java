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
    void getAllDescriptionsSuccess() {
        System.out.println("Starting getAllDescriptionsSuccess test...");

        // Retrieve all descriptions
        List<String> descriptions = dao.getAllDescriptions();

        // Perform assertions
        assertEquals(42, descriptions.size());
        assertTrue(descriptions.contains("Flight"));
        assertTrue(descriptions.contains("Super Strength"));

        System.out.println("Ending getAllDescriptionsSuccess test...");
    }
    @Test
    void update() {
        System.out.println("Starting updateSuccess test...");
        Hero hero;
        hero = heroDao.getById(1);
        // Insert a new Powers entity
        Powers power = new Powers("Fire Manipulation", "test", hero);
        int insertedPowerId = dao.insert(power);

        // Retrieve the inserted Powers entity
        Powers insertedPower = dao.getById(insertedPowerId);

        // Update the description of the Powers entity
        insertedPower.setDescription("Pyrokinesis");

        // Update the Powers entity in the database
        dao.update(insertedPower);

        // Retrieve the updated Powers entity from the database
        Powers updatedPower = dao.getById(insertedPowerId);

        // Perform assertions
        assertNotNull(updatedPower);
        assertEquals("Pyrokinesis", updatedPower.getDescription());

        System.out.println("Ending updateSuccess test...");
    }


    @Test
    void getByIdSuccess() {
        System.out.println("Starting getByIdSuccess test...");

        Hero hero = new Hero("Windchild", "Lance Talon", "bio", "Good", "test", "test", "test", "test", "test", "test");
        heroDao.insert(hero);

        Powers powers = new Powers("Windchild", "test", hero);
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
        // Define the updated values
        String updatedHeroCodeName = "Updated CodeName";
        String updatedHeroRealName = "Updated RealName";

        // Retrieve the existing Hero with ID 1
        Hero heroToUpdate = heroDao.getById(1);

        // Update the Hero's properties
        heroToUpdate.setCodeName(updatedHeroCodeName);
        heroToUpdate.setRealName(updatedHeroRealName);

        // Update the Hero in the database
        heroDao.update(heroToUpdate);

        // Retrieve the updated Hero from the database
        Hero updatedHero = heroDao.getById(1);

        // Perform assertions
        assertEquals(updatedHeroCodeName, updatedHero.getCodeName());
        assertEquals(updatedHeroRealName, updatedHero.getRealName());
    }


    @Test
    void deleteSuccess() {
        System.out.println("Starting deleteSuccess test...");

        Hero hero = new Hero("TestHero", "TestRealName", "testBio", "testAlignment", "test", "test", "test", "test", "test", "test");
        heroDao.insert(hero);

        Powers powersToDelete = new Powers("TestDescription", "test", hero);
        dao.insert(powersToDelete);

        dao.delete(powersToDelete);
        assertNull(dao.getById(powersToDelete.getPowerID()));

        System.out.println("Ending deleteSuccess test...");
    }

    @Test
    void getAllSuccess() {
        System.out.println("Starting getAllSuccess test...");
        List<Powers> powers = dao.getAllPowers();
        assertEquals(42, powers.size()); // Check for the inserted entries only

        System.out.println("Ending getAllSuccess test...");
    }
    @Test
    void insertSuccess() {
        System.out.println("Starting insertSuccess test...");
        Hero hero;
        hero = heroDao.getById(1);
        // Create a new Powers instance
        Powers power = new Powers("Telekinesis", "test", hero);

        // Insert the power
        int insertedPowerId = dao.insert(power);

        // Retrieve the inserted power
        Powers retrievedPower = dao.getById(insertedPowerId);

        // Perform assertions
        assertNotNull(retrievedPower);
        assertEquals("Telekinesis", retrievedPower.getDescription());

        System.out.println("Ending insertSuccess test...");
    }


    @Test
    void deletePowersWithHeroSuccess() {
        // Create a test Hero
        Hero testHero = new Hero("TestHero", "TestRealName", "testBio", "testAlignment", "test", "test", "test", "test", "test", "test");
        heroDao.insert(testHero);

        // Create a test Power associated with the test Hero
        Powers testPower = new Powers("TestDescription", "test", testHero);
        dao.insert(testPower);

        // Retrieve the associated Hero before deletion
        Hero heroBeforeDeletion = testPower.getHero();

        // Delete the Powers with associated Hero
        dao.delete(testPower);

        // Verify that the Powers are deleted
        assertNull(dao.getById(testPower.getPowerID()));

        // Verify that the associated Hero is not deleted
        assertNotNull(heroDao.getById(heroBeforeDeletion.getHeroId()));
    }

    @Test
    void getByHeroIdSuccess() {
        System.out.println("Starting getByHeroIdSuccess test...");

        // Create a test Hero
        Hero testHero = new Hero("TestHero", "TestRealName", "testBio", "testAlignment", "test", "test", "test", "test", "test", "test");
        heroDao.insert(testHero);

        // Create test Powers associated with the test Hero
        Powers power1 = new Powers("TestDescription1", "test1", testHero);
        Powers power2 = new Powers("TestDescription2", "test2", testHero);
        dao.insert(power1);
        dao.insert(power2);

        // Retrieve powers associated with the test Hero
        List<Powers> powers = dao.getByHeroId(testHero.getHeroId());

        // Perform assertions
        assertNotNull(powers);
        assertEquals(2, powers.size());
        assertEquals("TestDescription1", powers.get(0).getDescription());
        assertEquals("TestDescription2", powers.get(1).getDescription());

        System.out.println("Ending getByHeroIdSuccess test...");
    }

}
