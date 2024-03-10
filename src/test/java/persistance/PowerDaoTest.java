package persistance;

import entity.Hero;
import entity.Powers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Unit test for PowersDao
 *
 * @author chstudebaker
 */
class PowerDaoTest {

    PowersDao dao;
    HeroDao heroDao;

    /**
     * Run set up tasks before each test:
     * 1. execute SQL which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();

        dao = new PowersDao();
        heroDao = new HeroDao();
    }

    /**
     * Verify successful retrieval of a Power
     */
    @Test
    void getByIdSuccess() {
        Powers retrievedPowers = dao.getById(1);
        assertEquals("Windchild", retrievedPowers.getDescription());
        assertEquals("Windchild", retrievedPowers.getHero().getCodeName());
        assertEquals("Lance Talon", retrievedPowers.getHero().getRealName());

    }
    /**
     * Verify successful insert of a Power
     */
    @Test
    void insertSuccess() {

        HeroDao herodao = new HeroDao();
        Hero hero = heroDao.getById(22);

        Powers power = new Powers("super strength", hero);

        int insertedPowerId = dao.insert(power);

        Powers retrievedPower = dao.getById(insertedPowerId);

        assertNotNull(retrievedPower);
        assertEquals("super strength", retrievedPower.getDescription());
        assertEquals("Crushstone", retrievedPower.getHero().getCodeName());
        assertEquals("Brock Pebble", retrievedPower.getHero().getRealName());
    }
    @Test
    void updateSuccess() {
        String heroCodeName = "Falinex";
        String heroRealName = "Paul Wyvernel";

        Powers PowersToUpdate = dao.getById(2);
        PowersToUpdate.getHero().setCodeName(heroCodeName);
        PowersToUpdate.getHero().setRealName(heroRealName);

        dao.update(PowersToUpdate);

        Powers PowersAfterUpdate = dao.getById(2);

        assertEquals(heroCodeName, PowersAfterUpdate.getHero().getCodeName());
        assertEquals(heroRealName, PowersAfterUpdate.getHero().getRealName());
    }
    
    

}