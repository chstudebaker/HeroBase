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

@WebServlet("/searchHero")
public class SearchHero extends HttpServlet {
    private HeroDao heroDao = new HeroDao();

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


        // Perform the hero search based on the selected criteria
        List<Hero> searchResults;
        if (searchCriteria != null && !searchCriteria.isEmpty() && searchTerm != null && !searchTerm.isEmpty()) {
            // Search based on the provided criteria
            searchResults = heroDao.searchHeroes(searchCriteria, searchTerm);
        } else {
            // Retrieve all heroes if no search criteria provided
            searchResults = heroDao.getAllHeroes();

        }

        // Set the search results as an attribute in the request
        request.setAttribute("heroes", searchResults);


        // Forward to the results page
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);
    }
}
