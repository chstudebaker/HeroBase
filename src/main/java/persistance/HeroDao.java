package persistance;

import entity.Hero;
import entity.Powers;
import jakarta.persistence.NoResultException;
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
import org.hibernate.query.Query;

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

    public List<Powers> getPowersForHero(int heroId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Powers> criteriaQuery = builder.createQuery(Powers.class);
            Root<Hero> heroRoot = criteriaQuery.from(Hero.class);
            criteriaQuery.select(heroRoot.join("powers").get("powerID")).where(builder.equal(heroRoot.get("heroId"), heroId));
            TypedQuery<Powers> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving powers for hero with ID: " + heroId, e);
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
    public int getHeroIdByCodeName(String codeName) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
            Root<Hero> root = criteriaQuery.from(Hero.class);
            criteriaQuery.select(root.get("id")).where(builder.equal(root.get("heroCodeName"), codeName));
            Query<Integer> query = session.createQuery(criteriaQuery);
            Integer heroId = query.uniqueResult();
            return heroId != null ? heroId : -1;
        } catch (NoResultException e) {
            return -1; // Return -1 if hero with given code name is not found
        }
    }
    public boolean insertHeroWithPower(Hero hero, Powers power) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(hero);
            power.setHero(hero); // Set the hero for the power
            session.save(power);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
