/**
 * Provides access to blogs in the blog table.
 */
package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Blog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class BlogDao {

    // Logger for BlogDao class
    private static final Logger logger = LogManager.getLogger(BlogDao.class);

    // GenericDao instance for performing database operations
    private final GenericDaoImpl<Blog> genericDao;

    /**
     * Initializes a BlogDao object with a GenericDaoImpl instance for Blog entity.
     */
    public BlogDao() {
        this.genericDao = new GenericDaoImpl<>(Blog.class);
    }

    /**
     * Retrieves all blogs from the database.
     * @return A list of all blogs.
     */
    public List<Blog> getAllBlogs() {
        return genericDao.getAll();
    }

    /**
     * Retrieves a blog by its ID.
     * @param id The ID of the blog to retrieve.
     * @return The blog with the specified ID, or null if not found.
     */
    public Blog getById(int id) {
        return genericDao.getById(id);
    }

    /**
     * Inserts a new blog into the database.
     * @param blog The blog to insert.
     * @return The ID of the inserted blog.
     */
    public int insert(Blog blog) {
        return genericDao.insert(blog);
    }

    /**
     * Updates an existing blog in the database.
     * @param blog The blog to update.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Blog blog) {
        return genericDao.update(blog);
    }

    /**
     * Deletes a blog from the database.
     * @param blog The blog to delete.
     * @return True if the deletion is successful, false otherwise.
     */
    public boolean delete(Blog blog) {
        return genericDao.delete(blog);
    }

    /**
     * Retrieves all blogs associated with a specific hero from the database.
     * @param heroId The ID of the hero.
     * @return A list of blogs associated with the specified hero.
     */
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
