package controller;

import entity.Hero;
import persistance.HeroDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/heroList", ""})
public class HeroList extends HttpServlet {
    private HeroDao heroDao = new HeroDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all heroes
        // Example logging in servlet
        List<Hero> allHeroes = heroDao.getAllHeroes();

        // Set the heroList as an attribute in the request
        request.setAttribute("heroList", allHeroes);

        // Forward to the hero list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
