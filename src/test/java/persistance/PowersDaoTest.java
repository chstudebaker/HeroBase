package persistance;

import entity.Hero;
import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PowersDaoTest {

    private static final Logger logger = LogManager.getLogger(PowersDaoTest.class);

    @Test
    void testGetAllPowers() {
        PowersDAO powersDAO = new PowersDAO();
        List<Powers> powersList = powersDAO.getAllPowers();

        assertNotNull(powersList);
        logger.info("Number of Powers: {}", powersList.size());
        powersList.forEach(power -> logger.info("Power: {}", power));
    }

    @Test
    void testAddPower() {
        Hero hero = new Hero();
        PowersDAO powersDAO = new PowersDAO();
        Powers newPower = new Powers("New Power Description", hero);

        int initialSize = powersDAO.getAllPowers().size();
        powersDAO.addPower(newPower);

        List<Powers> updatedPowers = powersDAO.getAllPowers();
        assertNotNull(updatedPowers);
        assertEquals(initialSize + 1, updatedPowers.size());

        // Additional assertions if needed
    }

    @Test
    void testUpdatePower() {
        // Create a new Powers instance with initial data
        Hero Hero = new Hero();
        Powers initialPower = new Powers("Original Description", Hero);

        // Save the initial data to the database
        PowersDAO powersDAO = new PowersDAO();
        powersDAO.addPower(initialPower);

        // Update the description
        String updatedDescription = "Updated Power Description";
        initialPower.setDescription(updatedDescription);

        // Call the updatePower method
        powersDAO.updatePower(initialPower);

        // Retrieve the updated data from the database
        Powers updatedPower = powersDAO.getPowerById(initialPower.getPowerID());

        // Assertions
        assertNotNull(updatedPower);
        assertEquals(updatedDescription, updatedPower.getDescription());
    }

    @Test
    void testDeletePower() {
        PowersDAO powersDAO = new PowersDAO();
        List<Powers> powersList = powersDAO.getAllPowers();
        int initialSize = powersList.size();

        if (!powersList.isEmpty()) {
            Powers powerToDelete = powersList.get(0);
            powersDAO.deletePower(powerToDelete);

            List<Powers> updatedPowers = powersDAO.getAllPowers();
            assertNotNull(updatedPowers);
            assertEquals(initialSize - 1, updatedPowers.size());

            // Additional assertions if needed
        }
    }
}
