/**
 * Servlet for editing various entities such as heroes, powers, equipment, and blogs.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.util.FileUploadHandler;
import com.chstudebaker.herobase.persistance.EquipmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/EditEquipment")
public class EditEquipment extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     * Retrieves entity information based on the provided type and forwards the request to the appropriate JSP page for editing.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            String equipmentIDParam = request.getParameter("equipmentId");
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
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }




    /**
     * Handles HTTP POST requests.
     * Determines the type of entity being edited and calls the appropriate method for handling the editing process.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST request to edit equipment
        editEquipment(request, response);
    }

    /**
     * Edits an equipment based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void editEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userID = request.getParameter("userId");
        String equipmentIDParam = request.getParameter("equipmentId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("images");

        // Validate that equipmentID is not empty
        if (equipmentIDParam == null || equipmentIDParam.isEmpty()) {
            // If equipmentID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse equipmentID to an integer
        int equipmentID = Integer.parseInt(equipmentIDParam);

        // Handle file upload and get the relative path
        String images = null;
        if (filePart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            images = fileUploadHandler.handleFileUpload(filePart);
        } else {
            EquipmentDao equipmentDao = new EquipmentDao();
            Equipment exisitingEquipment = equipmentDao.getById(equipmentID);
            images = exisitingEquipment.getImages();
        }

        // Update the name, description, and images of the existing equipment
        EquipmentDao equipmentDao = new EquipmentDao();
        Equipment updatedEquipment = equipmentDao.getById(equipmentID);
        updatedEquipment.setName(name);
        updatedEquipment.setDescription(description);
        updatedEquipment.setImages(images); // Set the relative path

        // Update the equipment in the database
        boolean success = equipmentDao.update(updatedEquipment);

        request.setAttribute("editedItemId", updatedEquipment.getHero());

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
