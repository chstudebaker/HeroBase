package controller;

import entity.Blog;
import entity.Equipment;
import entity.Hero;
import entity.Powers;
import org.apache.logging.log4j.Level;
import persistance.BlogDao;
import persistance.HeroDao;
import persistance.PowersDao;
import persistance.EquipmentDao;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to doPost method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");

        logger.log(Level.INFO, "Received POST request for entity type: " + entityType);

        if ("hero".equals(entityType)) {
            // Handle deletion of a hero
            logger.log(Level.INFO, "Deleting a hero");
            deleteHero(request, response);
        } else if ("power".equals(entityType)) {
            // Handle deletion of a power
            logger.log(Level.INFO, "Deleting a power");
            deletePower(request, response);
        } else if ("equipment".equals(entityType)) {
            // Handle deletion of equipment
            logger.log(Level.INFO, "Deleting equipment");
            deleteEquipment(request, response);
        } else if ("blog".equals(entityType)) {
            // Handle deletion of a blog
            logger.log(Level.INFO, "Deleting a blog");
            deleteBlog(request, response);
        } else {
            // Handle invalid or missing entity type
            logger.log(Level.WARN, "Invalid entity type: " + entityType);
            response.sendRedirect("error.jsp");
        }
    }


    private void deleteHero(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve heroId from request parameters
        String heroIdParam = request.getParameter("heroID");

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
        request.getRequestDispatcher("deleteItemResult.jsp").forward(request, response);
    }

    private void deletePower(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String powerIdParam = request.getParameter("powerID");

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
        request.getRequestDispatcher("deleteItemResult.jsp").forward(request, response);
    }

    private void deleteEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String equipmentIdParam = request.getParameter("equipmentId");

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

        request.getRequestDispatcher("deleteItemResult.jsp").forward(request, response);

    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve blogId from request parameters
        String blogIdParam = request.getParameter("blogId");

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
        request.getRequestDispatcher("deleteItemResult.jsp").forward(request, response);
    }
}
