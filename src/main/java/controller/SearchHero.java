package controller;

import entity.Hero;
import persistance.HeroDAO;

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
    private HeroDAO heroDao = new HeroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the search hero form page
        request.getRequestDispatcher("results.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve search criteria from the form
        System.out.println("Servlet doPost method is being executed.");  // Add this line for logging
        String searchCriteria = request.getParameter("searchCriteria");
        String searchTerm = request.getParameter("searchTerm");
        System.out.println("Search Criteria: " + searchCriteria);  // Add this line for logging
        System.out.println("Search Term: " + searchTerm);  // Add this line for logging

        // Perform the hero search based on the selected criteria
        List<Hero> searchResults;
        if (searchCriteria != null && !searchCriteria.isEmpty() && searchTerm != null && !searchTerm.isEmpty()) {
            // Search based on the provided criteria
            searchResults = heroDao.searchHeroes(searchCriteria, searchTerm);
            System.out.println("Number of Search Results: " + searchResults.size());
            System.out.println("Search Results: " + searchResults);
            // Add this line for logging
        } else {
            // Retrieve all heroes if no search criteria provided
            searchResults = heroDao.getAllHeroes();

        }

        // Set the search results as an attribute in the request
        request.setAttribute("heroes", searchResults);


        // Forward to the results page
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        System.out.println("forwarding to: " + dispatcher);
        dispatcher.forward(request, response);
        System.out.println("responce from: " + response);
    }
}
