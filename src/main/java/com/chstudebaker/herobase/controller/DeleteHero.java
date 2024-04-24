package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/DeleteHero")
public class DeleteHero extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteHero.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            // Handle deletion of a hero
            logger.log(Level.INFO, "Deleting a hero");
            deleteHero(request, response);
        } else {
            response.sendRedirect("only_users.jsp");
        }
    }

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

        // Attempt to delete the hero
        boolean success = heroDao.delete(hero);
        logger.debug("Deletion success: " + success);

        // Set attributes for forwarding the request
        request.setAttribute("success", success);
        request.setAttribute("deletedItemId", heroId);

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
