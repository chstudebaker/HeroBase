package persistance;

import entity.Hero;
import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Access heroes in the hero table.
 */
public class HeroDAO {

    private static final Logger logger = LogManager.getLogger(HeroDAO.class);
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Hero> getAllHeroes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hero", Hero.class).list();
        } catch (Exception e) {
            logger.error("Error retrieving all books", e);
            throw e;
        }
    }

    public List<Hero> searchHeroes(String searchCriteria, String searchTerm) {
        List<Hero> heroes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        // Build the SQL query with a prepared statement
        String sql = "SELECT * FROM Hero WHERE " + searchCriteria + " LIKE ?";
        logger.debug("Search SQL: {}", sql);

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setString(1, "%" + searchTerm + "%");
            ResultSet results = selectStatement.executeQuery();
            while (results.next()) {
                Hero hero = createHeroFromResults(results);
                heroes.add(hero);
                logger.debug("Search Result: {}", results);
            }
        } catch (Exception e) {
            logger.error("Error searching heroes in the database", e);
        } finally {
            database.disconnect();
        }
        return heroes;
    }

    private Hero createHeroFromResults(ResultSet results) throws SQLException {
        Hero hero = new Hero();
        hero.setCodeName(results.getString("codeName"));
        hero.setAlignment(results.getString("alignment"));
        hero.setRealName(results.getString("realName"));
        hero.setBio(results.getString("bio"));

        // Fetch associated Powers entities for the hero
        List<Powers> powersList = getPowersForHero(results.getInt("powerId"));
        logger.debug("Number of Powers: {}", powersList.size());
        hero.setPowersList(powersList);

        return hero;
    }

    private List<Powers> getPowersForHero(int powerId) {
        List<Powers> powersList = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        // Build the SQL query to fetch Powers for a specific hero
        String sql = "SELECT * FROM Powers WHERE powerId = ?";
        logger.debug("Fetch Powers SQL: {}", sql);

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setInt(1, powerId);
            ResultSet results = selectStatement.executeQuery();

            while (results.next()) {
                Powers powers = new Powers();
                powers.setDescription(results.getString("description"));
                // Set other properties if needed
                powersList.add(powers);
            }
        } catch (Exception e) {
            logger.error("Error fetching powers for hero from the database", e);
        } finally {
            database.disconnect();
        }

        return powersList;
    }
}
