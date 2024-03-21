package persistance;

import entity.Equipment;
import entity.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentDaoTest {

    EquipmentDao dao;
    HeroDao heroDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new EquipmentDao();
        heroDao = new HeroDao();
    }

    @Test
    void getAllEquipmentSuccess() {
        System.out.println("Starting getAllEquipmentSuccess test...");

        // Retrieve all equipment
        List<Equipment> equipmentList = dao.getAllEquipment();

        // Perform assertions
        assertNotNull(equipmentList);
        assertFalse(equipmentList.isEmpty());

        System.out.println("Ending getAllEquipmentSuccess test...");
    }

    @Test
    void getByIdSuccess() {
        System.out.println("Starting getByIdSuccess test...");

        // Retrieve equipment by ID
        Equipment equipment = dao.getById(1);

        // Perform assertions
        assertNotNull(equipment);

        System.out.println("Ending getByIdSuccess test...");
    }

    @Test
    void getByHeroIdSuccess() {
        System.out.println("Starting getByHeroIdSuccess test...");

        // Retrieve equipment by Hero ID
        List<Equipment> equipmentList = dao.getByHeroId(1);

        // Perform assertions
        assertNotNull(equipmentList);
        assertFalse(equipmentList.isEmpty());

        System.out.println("Ending getByHeroIdSuccess test...");
    }

    @Test
    void insertSuccess() {
        System.out.println("Starting insertSuccess test...");

        // Create a new Hero instance
        // Create a new Hero instance
        Hero hero = new Hero("Crushstone", "Brock Pebble", "placeholder bio", "good", "test", "test", "test", "test", "test", "test");

// Insert the hero
        int insertedHeroId = heroDao.insert(hero);

        // Retrieve the inserted hero
        Hero retrievedHero = heroDao.getById(insertedHeroId);

        // Create a new Equipment instance associated with the hero
        Equipment equipment = new Equipment("Sword", "test", "A powerful sword", retrievedHero);

        // Insert the equipment
        int insertedEquipmentId = dao.insert(equipment);

        // Retrieve the inserted equipment
        Equipment retrievedEquipment = dao.getById(insertedEquipmentId);

        // Perform assertions
        assertNotNull(retrievedEquipment);
        assertEquals("Sword", retrievedEquipment.getName());

        System.out.println("Ending insertSuccess test...");
    }

    @Test
    void updateSuccess() {
        System.out.println("Starting updateSuccess test...");

        // Retrieve an existing Equipment
        Equipment equipmentToUpdate = dao.getById(1);

        // Update the equipment's properties
        equipmentToUpdate.setDescription("An updated description");

        // Update the Equipment in the database
        boolean success = dao.update(equipmentToUpdate);

        // Retrieve the updated Equipment from the database
        Equipment updatedEquipment = dao.getById(1);

        // Perform assertions
        assertTrue(success);
        assertNotNull(updatedEquipment);
        assertEquals("An updated description", updatedEquipment.getDescription());

        System.out.println("Ending updateSuccess test...");
    }

    @Test
    void deleteSuccess() {
        System.out.println("Starting deleteSuccess test...");

        // Retrieve an existing Equipment to delete
        Equipment equipmentToDelete = dao.getById(1);

        // Delete the Equipment
        boolean success = dao.delete(equipmentToDelete);

        // Perform assertions
        assertTrue(success);
        assertNull(dao.getById(1));

        System.out.println("Ending deleteSuccess test...");
    }
}
