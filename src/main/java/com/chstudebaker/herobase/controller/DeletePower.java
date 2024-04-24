package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Powers;
import com.chstudebaker.herobase.persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/DeletePower")
public class DeletePower extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeletePower.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            // Handle deletion of a power
            logger.log(Level.INFO, "Deleting a power");
            deletePower(request, response);
        } else {
            response.sendRedirect("only_users.jsp");
        }
    }

    private void deletePower(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve powerId from request parameters
        String powerIdParam = request.getParameter("powerId");
        String userID = request.getParameter("userId");

        // Check if powerId parameter is missing or empty
        if (powerIdParam == null || powerIdParam.isEmpty()) {
            // Handle missing or empty powerId parameter
            logger.error("Power ID parameter is missing or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse powerId to an integer
        int powerId = Integer.parseInt(powerIdParam);

        // Retrieve power from database using PowerDao
        PowersDao powerDao = new PowersDao();
        Powers power = powerDao.getById(powerId);

        // Attempt to delete the power
        boolean success = powerDao.delete(power);
        logger.debug("Deletion success: " + success);

        // Set attributes for forwarding the request
        request.setAttribute("success", success);
        request.setAttribute("deletedItemId", powerId);

        // Forward the request to the JSP
        request.getRequestDispatcher("deleteItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
