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

@WebServlet("/AddHero")
public class AddHero extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroName = request.getParameter("heroName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String[] powers = request.getParameterValues("powers");
        //String addPowerRedirect = request.getParameter("addPowerRedirect");

        // Perform necessary actions to add the hero to the database
        // ...

        // Assuming you have a HeroDao for database operations
        HeroDao heroDao = new HeroDao();
        // Assuming you have a PowersDao for powers-related operations
        PowersDao powersDao = new PowersDao();

        // Create a Hero object
        Hero hero = new Hero(heroName, realName, bio, alignment);

        // Add powers to the hero
        if (powers != null) {
            for (String power : powers) {
                Powers existingPower = powersDao.getByDescription(power);
                if (existingPower == null) {
                    // Redirect to add power form or handle as needed
                    response.sendRedirect("addPower.jsp");
                    return;
                }
                hero.addPower(existingPower);
            }
        }

        // Insert the hero into the database
        int insertedHeroId = heroDao.insert(hero);

        // Redirect to a success page or display a success message
        response.sendRedirect("success.jsp");
    }
}
