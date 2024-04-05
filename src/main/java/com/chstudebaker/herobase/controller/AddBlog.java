package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.persistance.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddBlog")
public class AddBlog extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddBlog.class.getName());

    public static final String BLOG = "blog";

    /**
     * Handles HTTP GET requests.
     * Retrieves the entity type from the request and forwards the request to the corresponding JSP page for adding entities.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            if (BLOG.equals(entityType)) {
                logger.log(Level.INFO, "Forwarding to addHero.jsp");
                request.getRequestDispatcher("addHero.jsp").forward(request, response);
            } else {
                // Handle invalid or missing entity type
                logger.log(Level.WARNING, "Invalid entity type: " + entityType);
                response.sendRedirect("error.jsp");
            }
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }

    /**
     * Handles HTTP POST requests.
     * Determines the type of entity being added and calls the appropriate method for handling the addition process.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");

        if (BLOG.equals(entityType)) {
            // Handle addition of a hero
            logger.log(Level.INFO, "Adding a blog");
            addBlog(request, response);
        } else {
            // Handle invalid or missing entity type
            logger.log(Level.WARNING, "Invalid entity type: " + entityType);
            response.sendRedirect("error.jsp");
        }
    }


    private void addBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve request parameters
        String userID = request.getParameter("userId");
        String heroID = request.getParameter("heroID");
        String blogTitle = request.getParameter("blogTitle");
        String blogContent = request.getParameter("blogContent");

        // Create a new instance of Blog entity
        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        blog.setBlogContent(blogContent);

        // Set the hero ID on the Blog entity
        if (heroID != null && !heroID.isEmpty()) {
            blog.setHeroID(Integer.parseInt(heroID));
        }

        // Save the Blog entity
        BlogDao blogDao = new BlogDao();
        Integer insertedBlogId = blogDao.insert(blog);

        // Check if the blog was successfully inserted
        boolean success = insertedBlogId != null && insertedBlogId > 0;
        request.setAttribute("addedItemId", heroID);
        request.setAttribute("success", success);

        // Forward the request to the result page
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
