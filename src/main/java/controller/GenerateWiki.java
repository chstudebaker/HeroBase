package controller;

import entity.Hero;
import persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        // Set hero object as request attribute
        request.setAttribute("hero", hero);

        // Forward the request to the wiki.jsp page to display the generated content
        request.getRequestDispatcher("wiki.jsp").forward(request, response);
    }
}
