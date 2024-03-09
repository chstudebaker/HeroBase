package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PowersDAO {

    private static final Logger logger = LogManager.getLogger(PowersDAO.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Powers> getAllPowers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Powers", Powers.class).list();
        } catch (Exception e) {
            logger.error("Error retrieving all powers", e);
            throw e;
        }
    }

    public Powers getPowerById(int powerId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Powers.class, powerId);
        } catch (Exception e) {
            logger.error("Error retrieving power by ID: " + powerId, e);
            throw e;
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
}
