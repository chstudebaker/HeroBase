package controller;

import entity.Hero;
import entity.Powers;
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
        // Retrieve hero ID from the request parameter
        int heroId = Integer.parseInt(request.getParameter("heroId"));

        // Fetch hero details from the database
        HeroDao heroDao = new HeroDao();
        Hero hero = heroDao.getById(heroId);

        // Fetch powers for the hero from the database
        PowersDao powerDao = new PowersDao();
        List<Powers> powers = powerDao.getByHeroId(heroId);

        // Set hero object and powers as request attributes
        request.setAttribute("hero", hero);
        request.setAttribute("powers", powers);

        // Forward the request to the wiki.jsp page to display the generated content
        request.getRequestDispatcher("wiki.jsp").forward(request, response);
    }
}
