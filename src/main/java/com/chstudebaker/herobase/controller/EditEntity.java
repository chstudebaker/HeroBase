/**
 * Servlet for editing various entities such as heroes, powers, equipment, and blogs.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.persistance.BlogDao;
import com.chstudebaker.herobase.util.FileUploadHandler;
import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.entity.Powers;
import com.chstudebaker.herobase.persistance.EquipmentDao;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/EditEntity")
public class EditEntity extends HttpServlet {

    // Constants for entity types
    public static final String HERO = "hero";
    public static final String POWER = "power";
    public static final String EQUIPMENT = "equipment";
    public static final String BLOG = "blog";

    /**
     * Handles HTTP GET requests.
     * Retrieves entity information based on the provided type and forwards the request to the appropriate JSP page for editing.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            if ("hero".equals(entityType)) {
                String heroIDParam = request.getParameter("heroId");
                if (heroIDParam == null || heroIDParam.isEmpty()) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                int heroID = Integer.parseInt(heroIDParam);
                HeroDao heroDao = new HeroDao();
                Hero hero = heroDao.getById(heroID);
                if (hero == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                request.setAttribute("hero", hero);
                request.getRequestDispatcher("editHero.jsp").forward(request, response);
            } else if ("power".equals(entityType)) {
                String powerIDParam = request.getParameter("powerID");
                if (powerIDParam == null || powerIDParam.isEmpty()) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                int powerID = Integer.parseInt(powerIDParam);
                PowersDao powersDao = new PowersDao();
                Powers power = powersDao.getById(powerID);
                if (power == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                request.setAttribute("power", power);
                request.getRequestDispatcher("editPower.jsp").forward(request, response);
            } else if ("equipment".equals(entityType)) {
                String equipmentIDParam = request.getParameter("equipmentId");
                if (equipmentIDParam == null || equipmentIDParam.isEmpty()) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                int equipmentID = Integer.parseInt(equipmentIDParam);
                EquipmentDao equipmentDao = new EquipmentDao();
                Equipment equipment = equipmentDao.getById(equipmentID);
                if (equipment == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }
                request.setAttribute("equipment", equipment);
                request.getRequestDispatcher("editEquipment.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
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
        // Retrieve entity type from request
        String entityType = request.getParameter("type");

        // Handle entity editing based on type
        if (HERO.equals(entityType)) {
            // Handle POST request to edit a hero
            editHero(request, response);
        } else if (POWER.equals(entityType)) {
            // Handle POST request to edit a power
            editPower(request, response);
        } else if (EQUIPMENT.equals(entityType)) {
            // Handle POST request to edit equipment
            editEquipment(request, response);
        } else if (BLOG.equals(entityType)) {
            // Handle POST request to edit a blog
            editBlog(request, response);
        } else {
            // Invalid or missing entity type
            response.sendRedirect("doPostError.jsp");
        }
    }

    /**
     * Edits a hero based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void editHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userID = request.getParameter("userId");
        String heroIDParam = request.getParameter("heroId");
        String codeName = request.getParameter("codeName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String descriptions = request.getParameter("descriptions");
        String personality = request.getParameter("personality");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        Part filePart = request.getPart("images");
        Part emblemPart = request.getPart("emblem");



        // Validate that heroID is not empty
        if (heroIDParam == null || heroIDParam.isEmpty()) {
            // If heroID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse heroID to an integer
        int heroId = Integer.parseInt(heroIDParam);

        // Handle file upload for images
        String images = null;
        if (filePart.getSize() > 0) { // Check if a new image is selected
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            images = fileUploadHandler.handleFileUpload(filePart);
        } else { // If no new image is selected, retain the existing image URL
            HeroDao heroDao = new HeroDao();
            Hero existingHero = heroDao.getById(heroId);
            images = existingHero.getImages();
        }

        // Handle file upload for emblem
        String emblem = null;
        if (emblemPart.getSize() > 0) { // Check if a new emblem is selected
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            emblem = fileUploadHandler.handleFileUpload(emblemPart);
        } else { // If no new emblem is selected, retain the existing emblem URL
            HeroDao heroDao = new HeroDao();
            Hero existingHero = heroDao.getById(heroId);
            emblem = existingHero.getEmblem();
        }

        // Create a Hero object with the updated information
        Hero updatedHero = new Hero();
        updatedHero.setHeroId(heroId);
        updatedHero.setCodeName(codeName);
        updatedHero.setRealName(realName);
        updatedHero.setBio(bio);
        updatedHero.setAlignment(alignment);
        updatedHero.setDescriptions(descriptions);
        updatedHero.setPersonality(personality);
        updatedHero.setHeight(height);
        updatedHero.setWeight(weight);
        updatedHero.setImages(images); // Set the relative path
        updatedHero.setEmblem(emblem);

        // Update the hero in the database
        HeroDao heroDao = new HeroDao();
        boolean success = heroDao.update(updatedHero);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Set the edited item ID attribute in the request
        request.setAttribute("editedItemId", heroId);

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);
    }


    /**
     * Edits a power based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void editPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Retrieve form data
        String userID = request.getParameter("userId");
        String powerIDParam = request.getParameter("powerID");
        String description = request.getParameter("description");
        String explanation = request.getParameter("explanation");

        // Validate that powerID is not empty
        if (powerIDParam == null || powerIDParam.isEmpty()) {
            // If powerID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp?userId=" + userID);
            return;
        }



        // Parse powerID to an integer
        int powerID = Integer.parseInt(powerIDParam);

        // Retrieve the existing power from the database
        PowersDao powerDao = new PowersDao();
        Powers existingPower = powerDao.getById(powerID);

        if (existingPower == null) {
            // Handle the case where the power doesn't exist
            response.sendRedirect("error.jsp?userId=" + userID);
            return;
        }

        // Update the description and explanation of the existing power
        existingPower.setDescription(description);
        existingPower.setExplanation(explanation);

        // Update the power in the database
        boolean success = powerDao.update(existingPower);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        request.setAttribute("editedItemId", existingPower.getHeroID());

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);    }

    /**
     * Edits an equipment based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void editEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userID = request.getParameter("userId");
        String equipmentIDParam = request.getParameter("equipmentId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("images");

        // Validate that equipmentID is not empty
        if (equipmentIDParam == null || equipmentIDParam.isEmpty()) {
            // If equipmentID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse equipmentID to an integer
        int equipmentID = Integer.parseInt(equipmentIDParam);

        // Handle file upload and get the relative path
        FileUploadHandler fileUploadHandler = new FileUploadHandler();
        String images = fileUploadHandler.handleFileUpload(filePart);

        // Retrieve the existing equipment from the database
        EquipmentDao equipmentDao = new EquipmentDao();
        Equipment existingEquipment = equipmentDao.getById(equipmentID);

        if (existingEquipment == null) {
            // Handle the case where the equipment doesn't exist
            response.sendRedirect("error.jsp");
            return;
        }



        // Update the name, description, and images of the existing equipment
        existingEquipment.setName(name);
        existingEquipment.setDescription(description);
        existingEquipment.setImages(images); // Set the relative path

        // Update the equipment in the database
        boolean success = equipmentDao.update(existingEquipment);

        request.setAttribute("editedItemId", existingEquipment.getHeroID());

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);    }

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
