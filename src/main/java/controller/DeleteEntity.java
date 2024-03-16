package controller;

import entity.Hero;
import persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteEntity")
public class DeleteEntity extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to doPost method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve entity type and ID from the request parameters
        String entityType = request.getParameter("type");
        String entityIdParam = request.getParameter("entityID");

        if (entityType == null || entityIdParam == null || entityIdParam.isEmpty()) {
            // If entity type or ID parameter is missing or empty, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Convert entity ID to integer
            int entityId = Integer.parseInt(entityIdParam);

            // Check the entity type and delete the corresponding entity
            switch (entityType) {
                case "hero":
                    deleteHero(entityId, response);
                    break;
                case "power":
                    deletePower(entityId, response);
                    break;
                case "equipment":
                    deleteEquipment(entityId, response);
                    break;
                default:
                    // Handle invalid entity type
                    response.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid entity ID format (not a number)
            response.sendRedirect("error.jsp");
        }
    }

    private void deleteHero(int heroId, HttpServletResponse response) throws IOException {
        HeroDao heroDao = new HeroDao();
        Hero hero = heroDao.getById(heroId);

        if (hero != null) {
            boolean success = heroDao.delete(hero);
            if (success) {
                response.sendRedirect("deleteSuccess.jsp?type=hero");
                return;
            }
        }
        response.sendRedirect("deleteFailure.jsp?type=hero");
    }

    private void deletePower(int powerId, HttpServletResponse response) throws IOException {

    }

    private void deleteEquipment(int equipmentId, HttpServletResponse response) throws IOException {

    }
}
