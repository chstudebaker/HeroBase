package controller;

import entity.Hero;
import persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editHero")
public class EditHero extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve heroID from the URL parameter
        String heroIDParam = request.getParameter("heroID");

        if (heroIDParam == null || heroIDParam.isEmpty()) {
            // If heroID parameter is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse the heroID parameter to an integer
        int heroID = Integer.parseInt(heroIDParam);

        // Fetch hero information from the database based on the heroID
        HeroDao heroDao = new HeroDao();
        Hero hero = heroDao.getById(heroID);

        if (hero == null) {
            // If hero is not found in the database, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Set hero attribute in the request scope
        request.setAttribute("hero", hero);

        // Forward the request to editHero.jsp
        request.getRequestDispatcher("editHero.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroIDParam = request.getParameter("heroId");
        String codeName = request.getParameter("codeName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String descriptions = request.getParameter("descriptions");
        String personality = request.getParameter("personality");
        String image = request.getParameter("image");

        // Validate that heroID is not empty
        if (heroIDParam == null || heroIDParam.isEmpty()) {
            // If heroID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse heroID to an integer
        int heroId = Integer.parseInt(heroIDParam);

        // Create a Hero object with the updated information
        Hero updatedHero = new Hero();
        updatedHero.setHeroId(heroId);
        updatedHero.setCodeName(codeName);
        updatedHero.setRealName(realName);
        updatedHero.setBio(bio);
        updatedHero.setAlignment(alignment);
        updatedHero.setDescriptions(descriptions);
        updatedHero.setPersonality(personality);
        updatedHero.setImages(image);

        // Update the hero in the database
        HeroDao heroDao = new HeroDao();
        boolean success = heroDao.update(updatedHero);

        if (success) {
            // If update is successful, redirect to a success page or display a success message
            response.sendRedirect("updateHeroSuccess.jsp");
        } else {
            // If update fails, redirect to an error page or display an error message
            response.sendRedirect("updateHeroFailure.jsp");
        }
    }
}
