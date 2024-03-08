// HeroDAOTest.java

package persistance;

import entity.Hero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroDaoTest {

    private final HeroDAO heroDAO = new HeroDAO();
    private static final Logger logger = LogManager.getLogger(HeroDaoTest.class);


    @Test
    void getAllHeroes() {
        // You can add data setup logic here if needed
        List<Hero> heroes = heroDAO.getAllHeroes();
        assertEquals(9, heroes.size());
    }

    @Test
    void testSearchHeroes() {
        String searchCriteria = "codeName";
        String searchTerm = "Super";
        List<Hero> searchResults = heroDAO.searchHeroes(searchCriteria, searchTerm);
        assertNotNull(searchResults);
        // Add more assertions based on your specific data
    }
}
