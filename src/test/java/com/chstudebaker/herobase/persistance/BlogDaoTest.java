package com.chstudebaker.herobase.persistance;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.entity.Hero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlogDaoTest {
    private static final Logger logger = LogManager.getLogger(BlogDaoTest.class);

    BlogDao dao;
    HeroDao heroDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        heroDao = new HeroDao();
        dao = new BlogDao();
    }

    @Test
    void getAllBlogsSuccess() {
        // Retrieve all blogs
        List<Blog> blogs = dao.getAllBlogs();

        // Perform assertions
        assertNotNull(blogs);
        assertEquals(0, blogs.size());
    }

    @Test
    void getByIdSuccess() {
        logger.info("Starting getByIdSuccess test...");

        Blog blog = new Blog();
        blog.setBlogTitle("Test Title");
        blog.setBlogContent("Test Content");
        dao.insert(blog);

        // Retrieve blog by ID
        Blog retrievedBlog = dao.getById(blog.getBlogId());

        // Perform assertions
        assertNotNull(retrievedBlog);
        assertEquals("Test Title", retrievedBlog.getBlogTitle());
        assertEquals("Test Content", retrievedBlog.getBlogContent());

        logger.info("Ending getByIdSuccess test...");
    }

    @Test
    void insertSuccess() {
        logger.info("Starting insertSuccess test...");

        Blog blog = new Blog();
        blog.setBlogTitle("Test Title");
        blog.setBlogContent("Test Content");

        // Insert the blog
        int insertedBlogId = dao.insert(blog);

        // Retrieve the inserted blog
        Blog retrievedBlog = dao.getById(insertedBlogId);

        // Perform assertions
        assertNotNull(retrievedBlog);
        assertEquals("Test Title", retrievedBlog.getBlogTitle());
        assertEquals("Test Content", retrievedBlog.getBlogContent());

        logger.info("Ending insertSuccess test...");
    }

    @Test
    void updateSuccess() {
        logger.info("Starting updateSuccess test...");

        // Insert a blog
        Blog blog = new Blog();
        blog.setBlogTitle("Test Title");
        blog.setBlogContent("Test Content");
        int insertedBlogId = dao.insert(blog);

        // Retrieve the inserted blog
        Blog retrievedBlog = dao.getById(insertedBlogId);

        // Update the blog
        retrievedBlog.setBlogTitle("Updated Title");
        retrievedBlog.setBlogContent("Updated Content");
        dao.update(retrievedBlog);

        // Retrieve the updated blog
        Blog updatedBlog = dao.getById(insertedBlogId);

        // Perform assertions
        assertNotNull(updatedBlog);
        assertEquals("Updated Title", updatedBlog.getBlogTitle());
        assertEquals("Updated Content", updatedBlog.getBlogContent());

        logger.info("Ending updateSuccess test...");
    }

    @Test
    void deleteSuccess() {
        logger.info("Starting deleteSuccess test...");

        // Insert a blog
        Blog blog = new Blog();
        blog.setBlogTitle("Test Title");
        blog.setBlogContent("Test Content");
        int insertedBlogId = dao.insert(blog);

        // Retrieve the inserted blog
        Blog retrievedBlog = dao.getById(insertedBlogId);

        // Delete the blog
        dao.delete(retrievedBlog);

        // Attempt to retrieve the deleted blog
        Blog deletedBlog = dao.getById(insertedBlogId);

        // Perform assertions
        assertNull(deletedBlog);

        logger.info("Ending deleteSuccess test...");
    }

    @Test
    void getByHeroIdSuccess() {
        logger.info("Starting getByHeroIdSuccess test...");

        // Insert a hero
        Hero hero = new Hero();
        hero.setCodeName("Test Hero");
        hero.setRealName("Test Real Name");
        heroDao.insert(hero);

        // Insert a blog associated with the hero
        Blog blog = new Blog();
        blog.setBlogTitle("Test Title");
        blog.setBlogContent("Test Content");
        blog.setHero(hero);
        dao.insert(blog);

        // Retrieve blogs by hero ID
        List<Blog> blogs = dao.getByHeroId(hero.getHeroId());

        // Perform assertions
        assertNotNull(blogs);
        assertEquals(1, blogs.size());
        assertEquals("Test Title", blogs.get(0).getBlogTitle());
        assertEquals("Test Content", blogs.get(0).getBlogContent());

        logger.info("Ending getByHeroIdSuccess test...");
    }
}
