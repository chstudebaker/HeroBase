/**
 * Provides access to equipment in the equipment table.
 */
package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Equipment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class EquipmentDao {

    // Logger for EquipmentDao class
    private static final Logger logger = LogManager.getLogger(EquipmentDao.class);

    // GenericDao instance for performing database operations
    private final GenericDaoImpl<Equipment> genericDao;

    /**
     * Initializes an EquipmentDao object with a GenericDaoImpl instance for Equipment entity.
     */
    public EquipmentDao() {
        this.genericDao = new GenericDaoImpl<>(Equipment.class);
    }

    /**
     * Retrieves all equipment from the database.
     * @return A list of all equipment.
     */
    public List<Equipment> getAllEquipment() {
        return genericDao.getAll();
    }

    /**
     * Retrieves equipment by its ID.
     * @param equipmentId The ID of the equipment to retrieve.
     * @return The equipment with the specified ID, or null if not found.
     */
    public Equipment getById(int equipmentId) {
        return genericDao.getById(equipmentId);
    }

    /**
     * Retrieves all equipment associated with a specific hero from the database.
     * @param heroId The ID of the hero.
     * @return A list of equipment associated with the specified hero.
     */
    public List<Equipment> getByHeroId(int heroId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Equipment> criteriaQuery = builder.createQuery(Equipment.class);
            Root<Equipment> root = criteriaQuery.from(Equipment.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("heroId"), heroId));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving equipment by hero ID: " + heroId, e);
            throw e;
        }
    }

    /**
     * Inserts a new equipment into the database.
     * @param equipment The equipment to insert.
     * @return The ID of the inserted equipment.
     */
    public int insert(Equipment equipment) {
        return genericDao.insert(equipment);
    }

    /**
     * Updates an existing equipment in the database.
     * @param equipment The equipment to update.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Equipment equipment) {
        return genericDao.update(equipment);
    }

    /**
     * Deletes equipment from the database.
     * @param equipment The equipment to delete.
     * @return True if the deletion is successful, false otherwise.
     */
    public boolean delete(Equipment equipment) {
        return genericDao.delete(equipment);
    }
}
