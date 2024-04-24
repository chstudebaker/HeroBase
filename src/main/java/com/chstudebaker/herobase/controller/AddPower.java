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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            logger.log(Level.INFO, "Forwarding to addPower.jsp");
            request.getRequestDispatcher("addPower.jsp").forward(request, response);
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle addition of a power
        logger.log(Level.INFO, "Adding equipment");
        addPower(request, response);
    }


    private void addPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve request parameters
        String userID = request.getParameter("userId");
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

