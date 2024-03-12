package controller;

import entity.Hero;
import persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddHero")
public class AddHero extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET request to display form for adding a hero
        // You can forward the request to your addHero.jsp or any other appropriate JSP page
        request.getRequestDispatcher("addHero.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroName = request.getParameter("heroName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        System.out.println("heroName: " + heroName);
        System.out.println("realName: " + realName);
        System.out.println("bio: " + bio);
        System.out.println("alignment: " + alignment);



        // Assuming you have a HeroDao for database operations
        HeroDao heroDao = new HeroDao();

        // Create a Hero object
        Hero hero = new Hero(heroName, realName, bio, alignment);

        // Insert the hero into the database
        int insertedHeroId = heroDao.insert(hero);

        // Check if the hero was successfully inserted
        if (insertedHeroId > 0) {
            // Hero addition was successful
            request.setAttribute("heroId", insertedHeroId);
            request.getRequestDispatcher("addPowerPrompt.jsp").forward(request, response);
        } else {
            // Hero addition failed
            response.sendRedirect("error.jsp");
        }
    }
}
