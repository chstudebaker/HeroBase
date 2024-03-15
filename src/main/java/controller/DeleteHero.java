package controller;

import persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteHero")
public class DeleteHero extends HttpServlet {

    private final HeroDao heroDao = new HeroDao(); // Create an instance of HeroDao

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the doPost method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve heroID from the request parameter
        String heroId = request.getParameter("heroId");

        if (heroId == null || heroId.isEmpty()) {
            // If heroID parameter is missing or empty, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Convert heroID to integer
            int id = Integer.parseInt(heroId);

            // Call a method to delete the hero from the database based on the ID and delete associated powers
            boolean success = heroDao.deleteHeroAndPowersById(id);

            if (success) {
                // If deletion is successful, redirect to a success page or display a success message
                response.sendRedirect("deleteHeroSuccess.jsp");
            } else {
                // If deletion fails, redirect to a failure page or display an error message
                response.sendRedirect("deleteHeroFailure.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid heroID format (not a number)
            response.sendRedirect("error.jsp");
        }
    }
}
