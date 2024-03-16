package controller;

import entity.Hero;
import entity.Powers;
import entity.Equipment;
import persistance.HeroDao;
import persistance.PowersDao;
import persistance.EquipmentDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddEntity")
public class AddEntity extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddEntity.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");

        logger.log(Level.INFO, "Received GET request for entity type: " + entityType);

        if ("hero".equals(entityType)) {
            logger.log(Level.INFO, "Forwarding to addHero.jsp");
            request.getRequestDispatcher("addHero.jsp").forward(request, response);
        } else if ("power".equals(entityType)) {
            // Retrieve power descriptions from the database
            PowersDao powersDao = new PowersDao();
            List<String> powerDescriptionsList = powersDao.getAllDescriptions();

            // Convert the list to a HashSet to get unique values
            Set<String> powerDescriptions = new HashSet<>(powerDescriptionsList);

            // Set power descriptions in request attributes
            request.setAttribute("powerDescriptions", powerDescriptions);

            // Forward the request to the JSP
            request.getRequestDispatcher("addPower.jsp").forward(request, response);
        }
        else if ("equipment".equals(entityType)) {
            logger.log(Level.INFO, "Forwarding to addEquipment.jsp");
            request.getRequestDispatcher("addEquipment.jsp").forward(request, response);
        } else {
            // Handle invalid or missing entity type
            logger.log(Level.WARNING, "Invalid entity type: " + entityType);
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");


        logger.log(Level.INFO, "Received POST request for entity type: " + entityType);
        if ("hero".equals(entityType)) {
            // Handle addition of a hero
            logger.log(Level.INFO, "Adding a hero");
            addHero(request, response);
        } else if ("power".equals(entityType)) {
            // Handle addition of a power
            logger.log(Level.INFO, "Adding a power");
            addPower(request, response);
        } else if ("equipment".equals(entityType)) {
            // Handle addition of equipment
            logger.log(Level.INFO, "Adding equipment");
            addEquipment(request, response);
        } else {
            // Handle invalid or missing entity type
            logger.log(Level.WARNING, "Invalid entity type: " + entityType);
            response.sendRedirect("error.jsp");
        }
    }

    private void addHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data for adding a hero
        String codeName = request.getParameter("codeName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String descriptions = request.getParameter("descriptions");
        String personality = request.getParameter("personality");
        String image = request.getParameter("image");

        // Assuming you have a HeroDao for database operations
        HeroDao heroDao = new HeroDao();

        // Create a Hero object
        Hero hero = new Hero(codeName, realName, bio, alignment, descriptions, personality, image);

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

    private void addPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroID = request.getParameter("heroID"); // Retrieve heroID from the form
        String selectedPower = request.getParameter("selectedPower"); // Retrieve selected power from the form
        String customPower = request.getParameter("customPower"); // Retrieve custom power from the form
        String explanation = request.getParameter("explanation");

        // Create a new instance of Powers entity
        Powers power = new Powers();
        power.setDescription(selectedPower != null && !selectedPower.isEmpty() ? selectedPower : customPower);
        power.setExplanation(explanation); // Set the explanation field

        // Set the HeroID on the Powers entity
        if (heroID != null && !heroID.isEmpty()) {
            power.setHeroID(Integer.parseInt(heroID));
        }

        PowersDao powersDao = new PowersDao();

        // Save the Powers entity
        Integer insertedPowerId = powersDao.insert(power);
        boolean success = insertedPowerId != null && insertedPowerId > 0;

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("addItemResult.jsp").forward(request, response);
    }


    private void addEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String heroID = request.getParameter("heroID"); // Retrieve heroID from the form
        String name = request.getParameter("name"); // Retrieve equipment name from the form
        String description = request.getParameter("description"); // Retrieve equipment description from the form

        // Create a new instance of Equipment entity
        Equipment equipment = new Equipment();
        equipment.setName(name); // Set the equipment name
        equipment.setDescription(description); // Set the equipment description

        // Set the hero ID on the Equipment entity
        if (heroID != null && !heroID.isEmpty()) {
            equipment.setHeroID(Integer.parseInt(heroID));
        }

        // Assuming you have an EquipmentDao for equipment-related operations
        EquipmentDao equipmentDao = new EquipmentDao();

        // Save the Equipment entity
        Integer insertedEquipmentId = equipmentDao.insert(equipment);

        // Check if the equipment was successfully inserted
        boolean success = insertedEquipmentId != null && insertedEquipmentId > 0;

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("addItemResult.jsp").forward(request, response);

    }
}

