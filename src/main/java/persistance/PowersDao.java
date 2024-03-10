package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PowersDao {

    private static final Logger logger = LogManager.getLogger(PowersDao.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Powers> getAllPowers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Powers", Powers.class).list();
        } catch (Exception e) {
            logger.error("Error retrieving all powers", e);
            throw e;
        }
    }

    public Powers getById(int id) {
        Session session = sessionFactory.openSession();
        Powers Powers = session.get( Powers.class, id );
        session.close();
        return Powers;
    }

    /**
     * update Powers
     * @param power  Powers to be inserted or updated
     */
    public void update(Powers power) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(power);
        transaction.commit();
        session.close();
    }

    /**
     * insert Powers
     * @param power  Powers to be inserted
     */
    public int insert(Powers power) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(power);
        transaction.commit();
        id = power.getPowerID();
        session.close();
        return id;
    }

    /**
     * Delete a Powers
     * @param power Powers to be deleted
     */
    public void delete(Powers power) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(power);
        transaction.commit();
        session.close();
    }



}
