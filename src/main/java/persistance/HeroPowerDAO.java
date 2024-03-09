package persistance;

import entity.HeroPower;
import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HeroPowerDAO {
    private Database database;

    private static final Logger logger = LogManager.getLogger(HeroDAO.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    public HeroPowerDAO() {
        this.database = Database.getInstance();
    }

    public void assignPowerToHero(int heroId, int powerId) {
        String sql = "INSERT INTO HeroPowers (HeroID, PowerID) VALUES (?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, heroId);
            statement.setInt(2, powerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
    public int insert(Powers power) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int id = (int) session.save(power);
            transaction.commit();
            return id;
        } catch (Exception e) {
            logger.error("Error inserting power: " + power, e);
            throw e;
        }
    }
    public HeroPower getHeroPowerById(int heroPowerId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(HeroPower.class, heroPowerId);
        } catch (Exception e) {
            logger.error("Error retrieving HeroPower by ID: " + heroPowerId, e);
            throw e;
        }
    }


    // Add other CRUD operations as necessary
}
