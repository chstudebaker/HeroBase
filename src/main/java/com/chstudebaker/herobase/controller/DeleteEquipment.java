package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Equipment;
import com.chstudebaker.herobase.persistance.EquipmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/DeleteEquipment")
public class DeleteEquipment extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteEquipment.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            // Handle deletion of a hero
            logger.log(Level.INFO, "Deleting a hero");
            deleteEquipment(request, response);
        } else {
            response.sendRedirect("only_users.jsp");
        }
    }

    private void deleteEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve equipmentId from request parameters
        String equipmentIdParam = request.getParameter("equipmentId");
        String userID = request.getParameter("userId");

        // Check if equipmentId parameter is missing or empty
        if (equipmentIdParam == null || equipmentIdParam.isEmpty()) {
            // Handle missing or empty equipmentId parameter
            logger.error("Equipment ID parameter is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse equipmentId to an integer
        int equipmentId = Integer.parseInt(equipmentIdParam);

        // Retrieve equipment from database using EquipmentDao
        EquipmentDao equipmentDao = new EquipmentDao();
        Equipment equipment = equipmentDao.getById(equipmentId);

        // Attempt to delete the equipment
        boolean success = equipmentDao.delete(equipment);
        logger.debug("Deletion success: " + success);

        // Set attributes for forwarding the request
        request.setAttribute("success", success);
        request.setAttribute("deletedItemId", equipmentId);

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }
}

