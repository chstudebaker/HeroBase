package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.persistance.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/DeleteBlog")
public class DeleteBlog extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteBlog.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            // Handle deletion of a hero
            logger.log(Level.INFO, "Deleting a hero");
            deleteBlog(request, response);
        } else {
            response.sendRedirect("only_users.jsp");
        }
    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve blogId from request parameters
        String blogIdParam = request.getParameter("blogId");
        String userID = request.getParameter("userId");

        // Check if blogId parameter is missing or empty
        if (blogIdParam == null || blogIdParam.isEmpty()) {
            // Handle missing or empty blogId parameter
            logger.error("Blog ID parameter is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse blogId to an integer
        int blogId = Integer.parseInt(blogIdParam);

        // Retrieve blog from database using BlogDao
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.getById(blogId);

        // Attempt to delete the blog
        boolean success = blogDao.delete(blog);
        logger.debug("Deletion success: " + success);

        // Set attributes for forwarding the request
        request.setAttribute("success", success);
        request.setAttribute("deletedItemId", blogId);

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
