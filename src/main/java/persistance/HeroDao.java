package persistance;

import entity.Hero;
import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Access heroes in the hero table.
 */
public class HeroDao {

    private static final Logger logger = LogManager.getLogger(HeroDao.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Hero> getAllHeroes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hero", Hero.class).list();
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
            String hql = "FROM Hero WHERE " + searchCriteria + " LIKE :searchTerm";
            TypedQuery<Hero> query = session.createQuery(hql, Hero.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%");

            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error searching heroes in the database", e);
            throw e;
        }
    }

    public List<Powers> getPowersForHero(int heroId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT hp.powerID FROM Hero hero JOIN hero.powers hp WHERE hero.heroId = :heroId";
            TypedQuery<Powers> query = session.createQuery(hql, Powers.class);
            query.setParameter("heroId", heroId);

            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving powers for hero with ID: " + heroId, e);
            throw e;
        }
    }

    /**
     * Update hero
     * @param hero Author to be updated
     */
    public void update(Hero hero) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(hero);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a new hero
     * @param hero Author to be inserted
     * @return The ID of the newly inserted hero
     */
    public int insert(Hero hero) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Ensure associated powers are set on the hero before persisting
        for (Powers power : hero.getPowers()) {
            session.persist(power);  // Explicitly persist each power
        }

        session.persist(hero);
        transaction.commit();
        id = hero.getHeroId();
        session.close();
        return id;
    }
    /**
     * Delete an hero
     * @param hero Hero to be deleted
     */
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
