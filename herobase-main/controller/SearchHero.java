package edu.matc.controller;

import edu.matc.entity.Hero;
import edu.matc.persistence.HeroData;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;

/**
 * A servlet to search heroes by code name.
 * @author chstudebaker
 */
@WebServlet(
        urlPatterns = {"/searchHero"}
)
public class SearchHero extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeName = req.getParameter("codeName"); // Get the code_name value from the request parameter

        if (codeName != null && !codeName.isEmpty()) {
            // If codeName is provided, perform the search
            HeroData heroData = new HeroData();
            List<Hero> hero = heroData.getHeroesByCriteria(codeName);
            req.setAttribute("heroes", hero);
        } else {
            // If codeName is not provided, retrieve all heroes
            HeroData heroData = new HeroData();
            req.setAttribute("heroes", heroData.getAllHeroes());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
