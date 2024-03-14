package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class PowersDao {

    private static final Logger logger = LogManager.getLogger(PowersDao.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Powers> getAllPowers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Powers> criteriaQuery = builder.createQuery(Powers.class);
            criteriaQuery.from(Powers.class);
            Query<Powers> query = session.createQuery(criteriaQuery); // No explicit typing
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all powers", e);
            throw e;
        }
    }
    /**
     * Retrieve powers associated with a specific hero ID.
     *
     * @param heroId The ID of the hero whose powers are to be retrieved.
     * @return A list of powers associated with the specified hero ID.
     */
    public List<Powers> getByHeroId(int heroId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Powers> criteriaQuery = builder.createQuery(Powers.class);
            Root<Powers> root = criteriaQuery.from(Powers.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("id"), heroId));
            Query<Powers> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving powers by hero ID: " + heroId, e);
            throw e;
        }
    }


    public Powers getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Powers.class, id);
        } catch (Exception e) {
            logger.error("Error retrieving power by ID: " + id, e);
            throw e;
        }
    }

    public void update(Powers power) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(power);
        transaction.commit();
        session.close();
    }

    public int insert(Powers power) {
        try (Session session = sessionFactory.openSession()) {
            logger.debug("Attempting to insert power: {}", power);

            Transaction transaction = session.beginTransaction();
            session.persist(power);
            transaction.commit();

            logger.debug("Power inserted successfully with ID: {}", power.getPowerID());

            return power.getPowerID();
        } catch (Exception e) {
            logger.error("Error inserting power", e);
            throw e;
        }
    }

    public void delete(Powers power) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(power);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Error deleting power", e);
            throw e;
        }
    }

    /**
     * Retrieve all power descriptions from the database.
     *
     * @return List of power descriptions.
     */
    public List<String> getAllDescriptions() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
            Root<Powers> root = criteriaQuery.from(Powers.class);

            // Select only the description column
            criteriaQuery.select(root.get("description"));

            Query<String> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all power descriptions", e);
            throw e;
        }
    }





}
