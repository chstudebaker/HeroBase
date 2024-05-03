package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/searchHero")
public class SearchHero extends HttpServlet {
    private HeroDao heroDao = new HeroDao();
    private static final Logger LOGGER = Logger.getLogger(SearchHero.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the search hero form page
        request.getRequestDispatcher("results.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve search criteria from the form
        String searchCriteria = request.getParameter("searchCriteria");
        String searchTerm = request.getParameter("searchTerm");

        // Log the search criteria
        LOGGER.log(Level.INFO, "Received search criteria: {0}, searchTerm: {1}", new Object[]{searchCriteria, searchTerm});

        // Perform the hero search based on the selected criteria
        List<Hero> searchResults;
        if (searchCriteria == null || searchCriteria.isEmpty()) {
            // If search criteria is blank, retrieve all heroes
            searchResults = heroDao.getAllHeroes();
        } else if (searchTerm != null && !searchTerm.isEmpty()) {
            // Search based on the provided criteria
            searchResults = heroDao.searchHeroes(searchCriteria, searchTerm);
        } else {
            // If search term is blank, retrieve all heroes
            searchResults = heroDao.getAllHeroes();
        }
        // Set the search results as an attribute in the request
        request.setAttribute("heroes", searchResults);

        // Forward to the results page
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);
    }
}
