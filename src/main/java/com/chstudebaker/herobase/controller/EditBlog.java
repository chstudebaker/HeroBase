/**
 * Servlet for editing various entities such as heroes, powers, blog, and blogs.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.persistance.BlogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/EditBlog")
public class EditBlog extends HttpServlet {


    /**
     * Handles HTTP GET requests.
     * Retrieves entity information based on the provided type and forwards the request to the appropriate JSP page for editing.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
                String blogIDParam = request.getParameter("blogId");
                if (blogIDParam == null || blogIDParam.isEmpty()) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                int blogID = Integer.parseInt(blogIDParam);
                BlogDao blogDao = new BlogDao();
                Blog blog = blogDao.getById(blogID);
                if (blog == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("editBlog.jsp").forward(request, response);
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }




    /**
     * Handles HTTP POST requests.
     * Determines the type of entity being edited and calls the appropriate method for handling the editing process.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request to edit a blog
        editBlog(request, response);
    }

    /**
     * Edits a blog based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void editBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userID = request.getParameter("userId");
        String blogIdParam = request.getParameter("blogId");
        String blogTitle = request.getParameter("blogTitle");
        String blogContent = request.getParameter("blogContent");

        // Validate that blogId is not empty
        if (blogIdParam == null || blogIdParam.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse blogId to an integer
        int blogId = Integer.parseInt(blogIdParam);

        // Retrieve the existing blog from the database
        BlogDao blogDao = new BlogDao();
        Blog existingBlog = blogDao.getById(blogId);

        if (existingBlog == null) {
            response.sendRedirect("error.jsp");
            return;
        }



        // Update the blog title, content, and images
        existingBlog.setBlogTitle(blogTitle);
        existingBlog.setBlogContent(blogContent);

        // Update the blog in the database
        boolean success = blogDao.update(existingBlog);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.setAttribute("editedItemId", existingBlog.getHeroID());
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);    }
}
