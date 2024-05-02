/**
 * Provides access to the hero table in the database.
 */
package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Hero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import java.util.List;

public class HeroDao {

    private static final Logger logger = LogManager.getLogger(HeroDao.class);
    private final GenericDaoImpl<Hero> genericDao;

    /**
     * Constructs a new HeroDao with a GenericDaoImpl instance for the Hero entity.
     */
    public HeroDao() {
        this.genericDao = new GenericDaoImpl<>(Hero.class);
    }

    /**
     * Retrieves all heroes from the hero table.
     * @return A list of all heroes.
     */
    public List<Hero> getAllHeroes() {
        return genericDao.getAll();
    }

    /**
     * Retrieves a hero by its ID.
     * @param heroId The ID of the hero to retrieve.
     * @return The hero with the specified ID, or null if not found.
     */
    public Hero getById(int heroId) {
        return genericDao.getById(heroId);
    }

    /**
     * Searches for heroes based on a specified criteria and search term.
     * @param searchCriteria The criteria to search for (e.g., "codeName", "realName").
     * @param searchTerm The term to search for.
     * @return A list of heroes matching the search criteria and term.
     */
    public List<Hero> searchHeroes(String searchCriteria, String searchTerm) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Hero> criteriaQuery = builder.createQuery(Hero.class);
            Root<Hero> root = criteriaQuery.from(Hero.class);
            Predicate predicate = builder.like(root.get(searchCriteria), "%" + searchTerm + "%");
            criteriaQuery.where(predicate);
            TypedQuery<Hero> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error searching heroes in the database", e);
            throw e;
        }
    }

    /**
     * Updates a hero in the database.
     * @param hero The hero to update.
     * @return True if the update is successful, otherwise false.
     */
    public boolean update(Hero hero) {
        return genericDao.update(hero);
    }

    /**
     * Inserts a new hero into the database.
     * @param hero The hero to insert.
     * @return The ID of the newly inserted hero.
     */
    public int insert(Hero hero) {
        return genericDao.insert(hero);
    }

    /**
     * Deletes a hero from the database.
     * @param hero The hero to delete.
     * @return True if the deletion is successful, otherwise false.
     */
    public boolean delete(Hero hero) {
        return genericDao.delete(hero);
    }

    /**
     * Retrieves heroes by UserId from the hero table.
     *
     * @param userId The UserId of the heroes to retrieve.
     * @return A list of heroes with the specified UserId.
     */
    public List<Hero> getHeroesByUserId(String userId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Hero> criteriaQuery = builder.createQuery(Hero.class);
            Root<Hero> root = criteriaQuery.from(Hero.class);
            Predicate predicate = builder.equal(root.get("userId"), userId);
            criteriaQuery.where(predicate);
            TypedQuery<Hero> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving heroes by UserId from the database", e);
            throw e;
        }
    }

}
