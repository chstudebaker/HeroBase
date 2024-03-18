package persistance;

import entity.Blog;
import entity.Hero;
import jakarta.persistence.criteria.Join;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class BlogDao {

    private static final Logger logger = LogManager.getLogger(BlogDao.class);
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<Blog> getAllBlogs() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Blog> criteriaQuery = builder.createQuery(Blog.class);
            criteriaQuery.from(Blog.class);
            Query<Blog> query = session.createQuery(criteriaQuery); // No explicit typing
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all blogs", e);
            throw e;
        }
    }

    public Blog getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Blog.class, id);
        } catch (Exception e) {
            logger.error("Error retrieving blog by ID: " + id, e);
            throw e;
        }
    }

    public int insert(Blog blog) {
        try (Session session = sessionFactory.openSession()) {
            logger.debug("Attempting to insert blog: {}", blog);

            Transaction transaction = session.beginTransaction();
            session.persist(blog);
            transaction.commit();

            logger.debug("Blog inserted successfully with ID: {}", blog.getBlogId());

            return blog.getBlogId();
        } catch (Exception e) {
            logger.error("Error inserting blog", e);
            throw e;
        }
    }

    public boolean update(Blog blog) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(blog);
            transaction.commit();
            return true; // If the update operation succeeds
        } catch (Exception e) {
            logger.error("Error updating blog", e);
            return false; // If an error occurs during the update operation
        }
    }

    public boolean delete(Blog blog) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(blog);
            transaction.commit();
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            logger.error("Error deleting blog", e);
            return false; // Return false if an error occurs during deletion
        }
    }

    public List<Blog> getByHeroId(int heroId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Blog> criteriaQuery = builder.createQuery(Blog.class);
            Root<Blog> root = criteriaQuery.from(Blog.class);
            criteriaQuery.select(root).where(builder.equal(root.get("hero").get("heroId"), heroId));
            Query<Blog> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving blog by hero ID: " + heroId, e);
            throw e;
        }
    }
}
