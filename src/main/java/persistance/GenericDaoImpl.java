package persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger logger = LogManager.getLogger(GenericDaoImpl.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    private final Class<T> entityType;

    public GenericDaoImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(entityType);
            criteriaQuery.from(entityType);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all entities", e);
            throw e;
        }
    }

    @Override
    public T getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityType, id);
        } catch (Exception e) {
            logger.error("Error retrieving entity by ID: " + id, e);
            throw e;
        }
    }

    @Override
    public int insert(T entity) {
        int id;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();

            id = (int) session.getIdentifier(entity);
        } catch (Exception e) {
            logger.error("Error inserting entity", e);
            throw e;
        }
        return id;
    }

    @Override
    public boolean update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return true; // If the update operation succeeds
        } catch (Exception e) {
            logger.error("Error updating entity", e);
            return false; // If an error occurs during the update operation
        }
    }

    @Override
    public boolean delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            logger.error("Error deleting entity", e);
            return false; // Return false if an error occurs during deletion
        }
    }
}
