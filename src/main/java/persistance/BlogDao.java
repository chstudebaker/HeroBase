package persistance;

import entity.Blog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * Access blogs in the blog table.
 */
public class BlogDao {

    private static final Logger logger = LogManager.getLogger(BlogDao.class);
    private final GenericDaoImpl<Blog> genericDao;

    public BlogDao() {
        this.genericDao = new GenericDaoImpl<>(Blog.class);
    }

    public List<Blog> getAllBlogs() {
        return genericDao.getAll();
    }

    public Blog getById(int id) {
        return genericDao.getById(id);
    }

    public int insert(Blog blog) {
        return genericDao.insert(blog);
    }

    public boolean update(Blog blog) {
        return genericDao.update(blog);
    }

    public boolean delete(Blog blog) {
        return genericDao.delete(blog);
    }

    public List<Blog> getByHeroId(int heroId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Blog> criteriaQuery = builder.createQuery(Blog.class);
            Root<Blog> root = criteriaQuery.from(Blog.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("heroId"), heroId));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving blog by hero ID: " + heroId, e);
            throw e;
        }
    }
}
