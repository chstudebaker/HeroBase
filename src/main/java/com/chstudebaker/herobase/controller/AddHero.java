package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.util.FileUploadHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddHero")
@MultipartConfig
public class AddHero extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddHero.class.getName());

    public static final String HERO = "hero";

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
            if (HERO.equals(entityType)) {
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

        if (HERO.equals(entityType)) {
            // Handle addition of hero
            logger.log(Level.INFO, "Adding hero");
            addHero(request, response);
        } else {
            // Handle invalid or missing entity type
            logger.log(Level.WARNING, "Invalid entity type: " + entityType);
            response.sendRedirect("error.jsp");
        }
    }



    private void addHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve request parameters
        String userID = request.getParameter("userId");
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

        // Handle file uploads separately
        FileUploadHandler fileUploadHandler = new FileUploadHandler();
        String images = fileUploadHandler.handleFileUpload(filePart);
        String emblem = fileUploadHandler.handleFileUpload(emblemPart);

        // Create a Hero object
        Hero hero = new Hero(codeName, realName, bio, alignment, images, descriptions, personality, height, weight, emblem);

        // Insert the hero into the database
        HeroDao heroDao = new HeroDao();
        Integer insertedHeroId = heroDao.insert(hero);

        // Check if the hero was successfully inserted
        boolean success = insertedHeroId != null && insertedHeroId > 0;
        request.setAttribute("addedItemId", insertedHeroId);
        request.setAttribute("success", success);

        // Forward the request to the result page
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
