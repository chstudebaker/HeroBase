package persistance;

import entity.Hero;
import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Access heroes in the hero table.
 */
public class HeroDao {

    private static final Logger logger = LogManager.getLogger(HeroDao.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Hero> getAllHeroes() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Hero> criteriaQuery = builder.createQuery(Hero.class);
            criteriaQuery.from(Hero.class);
            TypedQuery<Hero> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all heroes", e);
            throw e;
        }
    }

    public Hero getById(int heroId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Hero.class, heroId);
        } catch (Exception e) {
            logger.error("Error retrieving hero by ID: " + heroId, e);
            throw e;
        }
    }

    public List<Hero> searchHeroes(String searchCriteria, String searchTerm) {
        try (Session session = sessionFactory.openSession()) {
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

    public void update(Hero hero) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(hero);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Error updating hero", e);
            throw e;
        }
    }

    public int insert(Hero hero) {
        int id = 0;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Ensure associated powers are set on the hero before persisting
            for (Powers power : hero.getPowers()) {
                session.persist(power);  // Explicitly persist each power
            }

            session.persist(hero);
            transaction.commit();
            id = hero.getHeroId();
        } catch (Exception e) {
            logger.error("Error inserting hero", e);
            throw e;
        }
        return id;
    }

    public void delete(Hero hero) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Make sure the hero is attached to the session
            Hero attachedHero = session.get(Hero.class, hero.getHeroId());

            // Delete the hero and its associated powers
            session.delete(attachedHero);

            transaction.commit();
        } catch (Exception e) {
            logger.error("Error deleting hero and associated powers", e);
            throw e;
        }
    }



}
