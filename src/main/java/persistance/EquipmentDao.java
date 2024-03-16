package persistance;

import entity.Equipment;
import entity.Hero;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.TypedQuery;
import org.hibernate.query.Query;

import java.util.List;

public class EquipmentDao {

    private static final Logger logger = LogManager.getLogger(EquipmentDao.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Equipment> getAllEquipment() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Equipment> criteriaQuery = builder.createQuery(Equipment.class);
            criteriaQuery.from(Equipment.class);
            TypedQuery<Equipment> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all equipment", e);
            throw e;
        }
    }

    public Equipment getById(int equipmentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Equipment.class, equipmentId);
        } catch (Exception e) {
            logger.error("Error retrieving equipment by ID: " + equipmentId, e);
            throw e;
        }
    }

    public List<Equipment> getByHeroId(int heroId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Equipment> criteriaQuery = builder.createQuery(Equipment.class);
            Root<Equipment> root = criteriaQuery.from(Equipment.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("heroId"), heroId));
            Query<Equipment> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving equipment by hero ID: " + heroId, e);
            throw e;
        }
    }


    public int insert(Equipment equipment) {
        int id = 0;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(equipment);
            transaction.commit();
            id = equipment.getEquipmentId();
        } catch (Exception e) {
            logger.error("Error inserting equipment", e);
            throw e;
        }
        return id;
    }



    public boolean update(Equipment equipment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(equipment);
            transaction.commit();
            return true; // If the update operation succeeds
        } catch (Exception e) {
            logger.error("Error updating equipment", e);
            return false; // If an error occurs during the update operation
        }
    }

    public boolean delete(int equipmentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Equipment equipment = session.get(Equipment.class, equipmentId);
            if (equipment != null) {
                session.delete(equipment);
                transaction.commit();
                return true; // If deletion is successful
            } else {
                return false; // If equipment with the given ID is not found
            }
        } catch (Exception e) {
            logger.error("Error deleting equipment with ID: " + equipmentId, e);
            return false; // If an error occurs during deletion
        }
    }
}
