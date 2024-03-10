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
        dao = new PowersDao();
        heroDao = new HeroDao();
    }

    @Test
    void getByIdSuccess() {
        // Assuming there's a specific Hero and Powers with ID 1 for testing
        Hero hero = new Hero("Windchild", "Lance Talon", "", "");
        Powers powers = new Powers("Windchild", hero);
        heroDao.insert(hero);
        dao.insert(powers);

        Powers retrievedPowers = dao.getById(1);

        assertEquals("Windchild", retrievedPowers.getDescription());
        assertEquals("Windchild", retrievedPowers.getHero().getCodeName());
        assertEquals("Lance Talon", retrievedPowers.getHero().getRealName());
    }

    @Test
    void insertSuccess() {
        Hero hero = new Hero("Crushstone", "Brock Pebble", "", "");
        heroDao.insert(hero);

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
        // Assuming there's a specific Powers with ID 2 associated with a Hero for testing
        Hero hero = new Hero("Falinex", "Paul Wyvernel", "", "");
        heroDao.insert(hero);

        Powers powersToUpdate = new Powers("description", hero);
        dao.insert(powersToUpdate);

        String heroCodeName = "UpdatedFalinex";
        String heroRealName = "UpdatedPaulWyvernel";

        powersToUpdate.getHero().setCodeName(heroCodeName);
        powersToUpdate.getHero().setRealName(heroRealName);

        dao.update(powersToUpdate);

        Powers powersAfterUpdate = dao.getById(2);

        assertEquals(heroCodeName, powersAfterUpdate.getHero().getCodeName());
        assertEquals(heroRealName, powersAfterUpdate.getHero().getRealName());
    }

    @Test
    void deleteSuccess() {
        // Assuming there's a specific Powers with ID 12 for testing
        Hero hero = new Hero("TestHero", "TestRealName", "", "");
        heroDao.insert(hero);

        Powers powersToDelete = new Powers("TestDescription", hero);
        dao.insert(powersToDelete);

        dao.delete(powersToDelete);
        assertNull(dao.getById(12));
    }

    @Test
    void getAllSuccess() {
        // Assuming there are specific Powers entries for testing
        Hero hero1 = new Hero("Hero1", "RealName1", "", "");
        Hero hero2 = new Hero("Hero2", "RealName2", "", "");

        heroDao.insert(hero1);
        heroDao.insert(hero2);

        Powers powers1 = new Powers("Description1", hero1);
        Powers powers2 = new Powers("Description2", hero2);

        dao.insert(powers1);
        dao.insert(powers2);

        List<Powers> powers = dao.getAllPowers();
        assertEquals(13, powers.size()); // Assuming there are 11 existing entries plus 2 new entries
    }
}
