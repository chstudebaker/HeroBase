/**
 * Servlet for editing various entities such as heroes, powers, equipment, and blogs.
 */
package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Powers;
import com.chstudebaker.herobase.persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/Edit")
public class EditPower extends HttpServlet {

    public static final String POWER = "power";

    /**
     * Handles HTTP GET requests.
     * Retrieves entity information based on the provided type and forwards the request to the appropriate JSP page for editing.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            if ("power".equals(entityType)) {
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
            } else {
                response.sendRedirect("error.jsp");
            }
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
        // Retrieve entity type from request
        String entityType = request.getParameter("type");

        // Handle entity editing based on type
        if (POWER.equals(entityType)) {
            // Handle POST request to edit a power
            editPower(request, response);
        } else {
            // Invalid or missing entity type
            response.sendRedirect("doPostError.jsp");
        }
    }

    /**
     * Edits a power based on the provided request parameters and forwards the request to a result page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    private void editPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userID = request.getParameter("userId");
        String powerIDParam = request.getParameter("powerID");
        String description = request.getParameter("description");
        String explanation = request.getParameter("explanation");

        // Validate that powerID is not empty
        if (powerIDParam == null || powerIDParam.isEmpty()) {
            // If powerID is missing, redirect to an error page or handle the error appropriately
            response.sendRedirect("error.jsp?userId=" + userID);
            return;
        }

        // Parse powerID to an integer
        int powerID = Integer.parseInt(powerIDParam);

        // Retrieve the existing power from the database
        PowersDao powerDao = new PowersDao();
        Powers existingPower = powerDao.getById(powerID);

        if (existingPower == null) {
            // Handle the case where the power doesn't exist
            response.sendRedirect("error.jsp?userId=" + userID);
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
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
