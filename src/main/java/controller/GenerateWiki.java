package controller;

import entity.Equipment;
import entity.Hero;
import entity.Powers;
import entity.Blog;
import persistance.BlogDao;
import persistance.EquipmentDao;
import persistance.HeroDao;
import persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/generateWiki")
public class GenerateWiki extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

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

        EquipmentDao equipmentDao = new EquipmentDao();
        List<Equipment> equipment = equipmentDao.getByHeroId(heroId);

        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.getByHeroId(heroId);

        // Set hero object, powers, and userId as request attributes
        request.setAttribute("hero", hero);
        request.setAttribute("powers", powers);
        request.setAttribute("equipment", equipment);
        request.setAttribute("blogs", blogs);
        request.setAttribute("userId", userId); // Pass userId to the JSP

        // Forward the request to the wiki.jsp page to display the generated content
        request.getRequestDispatcher("wiki.jsp").forward(request, response);
    }
}
