package com.chstudebaker.herobase.controller;


import com.chstudebaker.herobase.entity.Powers;
import com.chstudebaker.herobase.persistance.PowersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/AddPower")
public class AddPower extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddPower.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        // Check if userID is null or empty
        if (userID == null || userID.isEmpty()) {
            // Handle null or empty userID
            logger.log(Level.WARNING, "User ID is null or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Redirect to addHero.jsp if userID is not null
        response.sendRedirect("addPower.jsp");
        response.sendRedirect("addPower.jsp");
    }


    private void addPower(HttpServletRequest request, HttpServletResponse response, String userID) throws ServletException, IOException {
        // Retrieve request parameters
        String heroID = request.getParameter("heroID");
        String selectedPower = request.getParameter("selectedPower");
        String customPower = request.getParameter("customPower");
        String explanation = request.getParameter("explanation");

        // Create a new instance of Powers entity
        Powers power = new Powers();
        power.setDescription(selectedPower != null && !selectedPower.isEmpty() ? selectedPower : customPower);
        power.setExplanation(explanation);

        // Set the HeroID on the Powers entity
        if (heroID != null && !heroID.isEmpty()) {
            power.setHeroID(Integer.parseInt(heroID));
        }

        // Save the Powers entity
        PowersDao powersDao = new PowersDao();
        Integer insertedPowerId = powersDao.insert(power);

        // Check if the power was successfully inserted
        boolean success = insertedPowerId != null && insertedPowerId > 0;
        request.setAttribute("addedItemId", heroID);
        request.setAttribute("success", success);

        // Forward the request to the result page
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }
}

