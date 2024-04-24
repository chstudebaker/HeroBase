package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.persistance.EquipmentDao;
import com.chstudebaker.herobase.util.FileUploadHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddEquipment")
@MultipartConfig
public class AddEquipment extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddEquipment.class.getName());

    /**
     * Handles HTTP GET requests.
     * Retrieves the entity type from the request and forwards the request to the corresponding JSP page for adding entities.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            logger.log(Level.INFO, "Forwarding to addEquipment.jsp");
            request.getRequestDispatcher("addEquipment.jsp").forward(request, response);
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }

    /**
     * Handles HTTP POST requests.
     * Determines the type of entity being added and calls the appropriate method for handling the addition process.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle addition of equipment
        logger.log(Level.INFO, "Adding equipment");
        addEquipment(request, response);
    }


    private void addEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve request parameters
        String userID = request.getParameter("userId");
        String heroID = request.getParameter("heroID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("images");

        // Handle file upload
        String images = null;
        if (filePart != null && filePart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            images = fileUploadHandler.handleFileUpload(filePart);
        }

        // Create a new instance of Equipment entity
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setDescription(description);
        equipment.setImages(images);

        // Set the hero ID on the Equipment entity
        if (heroID != null && !heroID.isEmpty()) {
            equipment.setHeroID(Integer.parseInt(heroID));
        }

        // Save the Equipment entity
        EquipmentDao equipmentDao = new EquipmentDao();
        Integer insertedEquipmentId = equipmentDao.insert(equipment);

        // Check if the equipment was successfully inserted
        boolean success = insertedEquipmentId != null && insertedEquipmentId > 0;
        request.setAttribute("addedItemId", heroID);
        request.setAttribute("success", success);

        // Forward the request to the result page
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
