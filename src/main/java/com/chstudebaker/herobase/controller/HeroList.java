/**
 * Servlet for retrieving and displaying a list of heroes.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/heroList", ""})
public class HeroList extends HttpServlet {

    // Data access object for handling hero-related database operations
    private HeroDao heroDao = new HeroDao();

    /**
     * Processes HTTP GET requests to retrieve and display a list of heroes.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve userId from the request
        String userId = request.getParameter("userId");

        // Retrieve all heroes from the database
        List<Hero> allHeroes = heroDao.getAllHeroes();

        // Set the heroList and userId as attributes in the request
        request.setAttribute("heroList", allHeroes);
        request.setAttribute("userId", userId); // Pass userId to the JSP

        // Forward to the hero list page (index.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
