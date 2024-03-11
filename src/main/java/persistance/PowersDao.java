package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            Transaction transaction = session.beginTransaction();
            session.persist(power);
            transaction.commit();
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
     * Retrieve a power by its description.
     *
     * @param description The description of the power.
     * @return The Powers object if found, or null if not found.
     */
    public Powers getByDescription(String description) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Powers> criteriaQueryDef = builder.createQuery(Powers.class);
            Root<Powers> root = criteriaQueryDef.from(Powers.class);
            Predicate predicate = builder.equal(root.get("description"), description);
            criteriaQueryDef.where(predicate);
            Query<Powers> query = session.createQuery(criteriaQueryDef); // No explicit typing
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error("Error retrieving power by description: " + description, e);
            throw e;
        }
    }

    public List<Powers> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Powers> query = builder.createQuery(Powers.class);
            Root<Powers> root = query.from(Powers.class);
            query.select(root); // Add this line to select all columns
            Query<Powers> criteriaQuery = session.createQuery(query);
            return criteriaQuery.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all powers", e);
            throw e;
        }
    }



}
