/**
 * Servlet for generating a wiki page for a specific hero, including their powers, equipment, and blogs.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Blog;
import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.entity.Powers;
import com.chstudebaker.herobase.persistance.BlogDao;
import com.chstudebaker.herobase.persistance.EquipmentDao;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/generateWiki")
public class GenerateWiki extends HttpServlet {

    /**
     * Processes HTTP GET requests by calling the processRequest method.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes HTTP POST requests by calling the processRequest method.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes the HTTP request to generate a wiki page for a specific hero.
     * Retrieves hero details, powers, equipment, and blogs from the database based on the hero ID.
     * Sets request attributes and forwards the request to the wiki.jsp page for display.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int heroId = Integer.parseInt(request.getParameter("heroId"));

        // Retrieve userId from the session
        String userId = (String) request.getSession().getAttribute("userId");

        // Fetch hero details from the database
        HeroDao heroDao = new HeroDao();
        Hero hero = heroDao.getById(heroId);

        // Fetch powers for the hero from the database
        PowersDao powerDao = new PowersDao();
        List<Powers> powers = powerDao.getByHeroId(heroId);

        // Fetch equipment for the hero from the database
        EquipmentDao equipmentDao = new EquipmentDao();
        List<Equipment> equipment = equipmentDao.getByHeroId(heroId);

        // Fetch blogs related to the hero from the database
        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.getByHeroId(heroId);

        // Set hero object, powers, equipment, and userId as request attributes
        request.setAttribute("hero", hero);
        request.setAttribute("powers", powers);
        request.setAttribute("equipment", equipment);
        request.setAttribute("blogs", blogs);
        request.setAttribute("userId", userId); // Pass userId to the JSP

        // Forward the request to the wiki.jsp page to display the generated content
        request.getRequestDispatcher("wiki.jsp").forward(request, response);
    }
}
