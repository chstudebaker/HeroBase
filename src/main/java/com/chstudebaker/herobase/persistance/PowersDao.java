/**
 * Provides access to powers in the powers table.
 */
package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import java.util.List;

public class PowersDao {

    private static final Logger logger = LogManager.getLogger(PowersDao.class);
    private final GenericDaoImpl<Powers> genericDao;

    /**
     * Constructs a new PowersDao with a GenericDaoImpl instance for the Powers entity.
     */
    public PowersDao() {
        this.genericDao = new GenericDaoImpl<>(Powers.class);
    }

    /**
     * Retrieves all powers from the powers table.
     * @return A list of all powers.
     */
    public List<Powers> getAllPowers() {
        return genericDao.getAll();
    }

    /**
     * Retrieve powers associated with a specific hero ID.
     * @param heroId The ID of the hero whose powers are to be retrieved.
     * @return A list of powers associated with the specified hero ID.
     */
    public List<Powers> getByHeroId(int heroId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Powers> criteriaQuery = builder.createQuery(Powers.class);
            Root<Powers> root = criteriaQuery.from(Powers.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("id"), heroId));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving powers by hero ID: " + heroId, e);
            throw e;
        }
    }

    /**
     * Retrieves a power by its ID.
     * @param id The ID of the power to retrieve.
     * @return The power with the specified ID, or null if not found.
     */
    public Powers getById(int id) {
        return genericDao.getById(id);
    }

    /**
     * Updates a power in the database.
     * @param power The power to update.
     * @return True if the update is successful, otherwise false.
     */
    public boolean update(Powers power) {
        return genericDao.update(power);
    }

    /**
     * Inserts a new power into the database.
     * @param power The power to insert.
     * @return The ID of the newly inserted power.
     */
    public int insert(Powers power) {
        return genericDao.insert(power);
    }

    /**
     * Deletes a power from the database.
     * @param power The power to delete.
     * @return True if the deletion is successful, otherwise false.
     */
    public boolean delete(Powers power) {
        return genericDao.delete(power);
    }

    /**
     * Retrieve all power descriptions from the database.
     * @return List of power descriptions.
     */
    public List<String> getAllDescriptions() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
            Root<Powers> root = criteriaQuery.from(Powers.class);

            // Select only the description column
            criteriaQuery.select(root.get("description"));

            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all power descriptions", e);
            throw e;
        }
    }
}
