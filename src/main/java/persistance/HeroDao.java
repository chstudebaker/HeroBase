package persistance;

import entity.Hero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import java.util.List;

/**
 * Access heroes in the hero table.
 */
public class HeroDao {

    private static final Logger logger = LogManager.getLogger(HeroDao.class);
    private final GenericDaoImpl<Hero> genericDao;

    public HeroDao() {
        this.genericDao = new GenericDaoImpl<>(Hero.class);
    }

    public List<Hero> getAllHeroes() {
        return genericDao.getAll();
    }

    public Hero getById(int heroId) {
        return genericDao.getById(heroId);
    }

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

    public boolean update(Hero hero) {
        return genericDao.update(hero);
    }

    public int insert(Hero hero) {
        return genericDao.insert(hero);
    }

    public boolean delete(Hero hero) {
        return genericDao.delete(hero);
    }
}
