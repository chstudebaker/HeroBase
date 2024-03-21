/**
 * Servlet for deleting various entities such as heroes, powers, equipment, and blogs.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.entity.Powers;
import org.apache.logging.log4j.Level;
import com.chstudebaker.herobase.persistance.BlogDao;
import com.chstudebaker.herobase.persistance.EquipmentDao;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.persistance.PowersDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteEntity")
public class DeleteEntity extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteEntity.class);
    public static final String HERO = "hero";
    public static final String POWER = "power";
    public static final String EQUIPMENT = "equipment";
    public static final String BLOG = "blog";

    /**
     * Handles HTTP GET requests.
     * Forwards the request to the doPost method.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to doPost method
        doPost(request, response);
    }

    /**
     * Handles HTTP POST requests.
     * Determines the type of entity being deleted and calls the appropriate method for handling the deletion process.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");
        String userID = request.getParameter("userId");

        logger.log(Level.INFO, "Received POST request for entity type: " + entityType);
        if (userID != null && !userID.isEmpty()) {
            if (HERO.equals(entityType)) {
                // Handle deletion of a hero
                logger.log(Level.INFO, "Deleting a hero");
                deleteHero(request, response);
            } else if (POWER.equals(entityType)) {
                // Handle deletion of a power
                logger.log(Level.INFO, "Deleting a power");
                deletePower(request, response);
            } else if (EQUIPMENT.equals(entityType)) {
                // Handle deletion of equipment
                logger.log(Level.INFO, "Deleting equipment");
                deleteEquipment(request, response);
            } else if (BLOG.equals(entityType)) {
                // Handle deletion of a blog
                logger.log(Level.INFO, "Deleting a blog");
                deleteBlog(request, response);
            } else {
                // Handle invalid or missing entity type
                logger.log(Level.WARN, "Invalid entity type: " + entityType);
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("only_users.jsp");
        }
    }

    /**
     * Deletes a hero based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    private void deleteHero(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve heroId from request parameters
        String heroIdParam = request.getParameter("heroId");
        String userID = request.getParameter("userId");
        // Check if heroId parameter is missing or empty
        if (heroIdParam == null || heroIdParam.isEmpty()) {
            // Handle missing or empty heroId parameter
            logger.error("Hero ID parameter is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse heroId to an integer
        int heroId = Integer.parseInt(heroIdParam);

        // Retrieve hero from database using HeroDao
        HeroDao heroDao = new HeroDao();
        Hero hero = heroDao.getById(heroId);

        boolean success = heroDao.delete(hero);
        logger.debug("Deletion success: " + success);

        // Set attributes for forwarding the request
        request.setAttribute("success", success);
        request.setAttribute("deletedItemId", heroId);

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }

    /**
     * Deletes a power based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    private void deletePower(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String powerIdParam = request.getParameter("powerID");
        String userID = request.getParameter("userId");
        if (powerIdParam == null || powerIdParam.isEmpty()) {
            logger.error("Power ID parameter is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        int powerId = Integer.parseInt(powerIdParam);

        PowersDao powerDao = new PowersDao();
        Powers power = powerDao.getById(powerId);

        if (power == null) {
            logger.error("Power with ID " + powerId + " not found.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Attempt to delete the power
        boolean success = powerDao.delete(power);
        logger.debug("Deletion success: " + success);

        request.setAttribute("success", success);

        request.setAttribute("deletedItemId", power.getHero().getHeroId());

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }

    /**
     * Deletes an equipment based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    private void deleteEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String equipmentIdParam = request.getParameter("equipmentId");
        String userID = request.getParameter("userId");
        if (equipmentIdParam == null || equipmentIdParam.isEmpty()) {
            logger.error("Equipment ID parameter is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        int equipmentId = Integer.parseInt(equipmentIdParam);

        EquipmentDao equipmentDao = new EquipmentDao();
        Equipment equipment = equipmentDao.getById(equipmentId);

        if (equipment == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        boolean success = equipmentDao.delete(equipment);


        request.setAttribute("deletedItemId", equipment.getHero().getHeroId());

        request.setAttribute("success", success);

        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);

    }

    /**
     * Deletes an blog based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws IOException If an I/O error occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
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

        // Check if the blog exists
        if (blog == null) {
            // Handle the case where the blog doesn't exist
            logger.error("Blog with ID " + blogId + " not found.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Attempt to delete the blog
        boolean success = blogDao.delete(blog);
        logger.debug("Deletion success: " + success);

        // Set attributes for forwarding the request
        request.setAttribute("success", success);
        request.setAttribute("deletedItemId", blog.getHero().getHeroId());

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
