package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.entity.Hero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentDaoTest {
    private static final Logger logger = LogManager.getLogger(BlogDaoTest.class);

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
        logger.info("Starting getAllEquipmentSuccess test...");

        // Retrieve all equipment
        List<Equipment> equipmentList = dao.getAllEquipment();

        // Perform assertions
        assertNotNull(equipmentList);
        assertFalse(equipmentList.isEmpty());

        logger.info("Ending getAllEquipmentSuccess test...");
    }

    @Test
    void getByIdSuccess() {
        logger.info("Starting getByIdSuccess test...");

        // Retrieve equipment by ID
        Equipment equipment = dao.getById(1);

        // Perform assertions
        assertNotNull(equipment);

        logger.info("Ending getByIdSuccess test...");
    }

    @Test
    void getByHeroIdSuccess() {
        logger.info("Starting getByHeroIdSuccess test...");

        // Retrieve equipment by Hero ID
        List<Equipment> equipmentList = dao.getByHeroId(1);

        // Perform assertions
        assertNotNull(equipmentList);
        assertFalse(equipmentList.isEmpty());

        logger.info("Ending getByHeroIdSuccess test...");
    }

    @Test
    void insertSuccess() {
        logger.info("Starting insertSuccess test...");

        // Create a new Hero instance
        // Create a new Hero instance
        Hero hero = new Hero("Crushstone", "Brock Pebble", "placeholder bio", "good", "test", "test", "test", "test",
                "test", "test", "test");

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

        logger.info("Ending insertSuccess test...");
    }

    @Test
    void updateSuccess() {
        logger.info("Starting updateSuccess test...");

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

        logger.info("Ending updateSuccess test...");
    }

    @Test
    void deleteSuccess() {
        logger.info("Starting deleteSuccess test...");

        // Retrieve an existing Equipment to delete
        Equipment equipmentToDelete = dao.getById(1);

        // Delete the Equipment
        boolean success = dao.delete(equipmentToDelete);

        // Perform assertions
        assertTrue(success);
        assertNull(dao.getById(1));

        logger.info("Ending deleteSuccess test...");
    }
}
