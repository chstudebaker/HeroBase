package controller;

import entity.Equipment;
import entity.Hero;
import entity.Powers;
import persistance.EquipmentDao;
import persistance.HeroDao;
import persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditEntity")
public class EditEntity extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");

        if ("hero".equals(entityType)) {
            String heroIDParam = request.getParameter("heroID");
            if (heroIDParam == null || heroIDParam.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }
            int heroID = Integer.parseInt(heroIDParam);
            HeroDao heroDao = new HeroDao();
            Hero hero = heroDao.getById(heroID);
            if (hero == null) {
                response.sendRedirect("error.jsp");
                return;
            }
            request.setAttribute("hero", hero);
            request.getRequestDispatcher("editHero.jsp").forward(request, response);
        } else if ("power".equals(entityType)) {
            String powerIDParam = request.getParameter("powerID");
            if (powerIDParam == null || powerIDParam.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }
            int powerID = Integer.parseInt(powerIDParam);
            PowersDao powersDao = new PowersDao();
            Powers power = powersDao.getById(powerID);
            if (power == null) {
                response.sendRedirect("error.jsp");
                return;
            }
            request.setAttribute("power", power);
            request.getRequestDispatcher("editPower.jsp").forward(request, response);
        } else if ("equipment".equals(entityType)) {
            String equipmentIDParam = request.getParameter("equipmentID");
            if (equipmentIDParam == null || equipmentIDParam.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }
            int equipmentID = Integer.parseInt(equipmentIDParam);
            EquipmentDao equipmentDao = new EquipmentDao();
            Equipment equipment = equipmentDao.getById(equipmentID);
            if (equipment == null) {
                response.sendRedirect("error.jsp");
                return;
            }
            request.setAttribute("equipment", equipment);
            request.getRequestDispatcher("editEquipment.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");

        if ("hero".equals(entityType)) {
            // Handle POST request to edit a hero
            editHero(request, response);
        } else if ("power".equals(entityType)) {
            // Handle POST request to edit a power
            editPower(request, response);
        } else if ("equipment".equals(entityType)) {
            // Handle POST request to edit equipment
            editEquipment(request, response);
        } else {
            // Handle invalid or missing entity type
            response.sendRedirect("error.jsp");
        }
    }

    private void editHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroIDParam = request.getParameter("heroId");
        String codeName = request.getParameter("codeName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String descriptions = request.getParameter("descriptions");
        String personality = request.getParameter("personality");
        String images = request.getParameter("images");

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
        updatedHero.setImages(images);

        // Update the hero in the database
        HeroDao heroDao = new HeroDao();
        boolean success = heroDao.update(updatedHero);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        request.setAttribute("editedItemId", heroId);

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp").forward(request, response);
    }
    private void editPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String powerIDParam = request.getParameter("powerID");
        String description = request.getParameter("description");
        String explanation = request.getParameter("explanation");

        // Validate that powerID is not empty
        if (powerIDParam == null || powerIDParam.isEmpty()) {
            // If powerID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse powerID to an integer
        int powerID = Integer.parseInt(powerIDParam);

        // Retrieve the existing power from the database
        PowersDao powerDao = new PowersDao();
        Powers existingPower = powerDao.getById(powerID);

        if (existingPower == null) {
            // Handle the case where the power doesn't exist
            response.sendRedirect("error.jsp");
            return;
        }

        // Update the description and explanation of the existing power
        existingPower.setDescription(description);
        existingPower.setExplanation(explanation);

        // Update the power in the database
        boolean success = powerDao.update(existingPower);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        request.setAttribute("editedItemId", existingPower.getHeroID());

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp").forward(request, response);
    }

    private void editEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String equipmentIDParam = request.getParameter("equipmentID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String images = request.getParameter("images");

        // Validate that equipmentID is not empty
        if (equipmentIDParam == null || equipmentIDParam.isEmpty()) {
            // If equipmentID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse equipmentID to an integer
        int equipmentID = Integer.parseInt(equipmentIDParam);

        // Retrieve the existing equipment from the database
        EquipmentDao equipmentDao = new EquipmentDao();
        Equipment existingEquipment = equipmentDao.getById(equipmentID);

        if (existingEquipment == null) {
            // Handle the case where the equipment doesn't exist
            response.sendRedirect("error.jsp");
            return;
        }

        // Update the name and description of the existing equipment
        existingEquipment.setName(name);
        existingEquipment.setDescription(description);
        existingEquipment.setImages(images);

        // Update the equipment in the database
        boolean success = equipmentDao.update(existingEquipment);

        request.setAttribute("editedItemId", existingEquipment.getHeroID());

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp").forward(request, response);
    }



}
