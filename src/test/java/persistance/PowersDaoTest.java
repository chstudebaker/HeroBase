package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
