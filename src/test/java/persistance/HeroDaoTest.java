// HeroDAOTest.java

package persistance;

import entity.Hero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroDAOTest {

    private final HeroDAO heroDAO = new HeroDAO();

    @Test
    void testGetAllHeroes() {
        List<Hero> heroes = heroDAO.getAllHeroes();
        assertNotNull(heroes);
        assertFalse(heroes.isEmpty());
        // Add more assertions based on your specific data
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
