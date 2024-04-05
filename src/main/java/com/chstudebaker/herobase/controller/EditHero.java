package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.util.FileUploadHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/EditHero")
public class EditHero extends HttpServlet {


    // Constants for entity types
    public static final String HERO = "hero";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            if ("hero".equals(entityType)) {
                String heroIDParam = request.getParameter("heroId");
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
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve entity type from request
        String entityType = request.getParameter("type");

        // Handle entity editing based on type
        if (HERO.equals(entityType)) {
            // Handle POST request to edit a hero
            editHero(request, response);
        } else {
            // Invalid or missing entity type
            response.sendRedirect("doPostError.jsp");
        }
    }
    protected void editHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");
        String heroIDParam = request.getParameter("heroId");
        String codeName = request.getParameter("codeName");
        String realName = request.getParameter("realName");
        String bio = request.getParameter("bio");
        String alignment = request.getParameter("alignment");
        String descriptions = request.getParameter("descriptions");
        String personality = request.getParameter("personality");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        Part filePart = request.getPart("images");
        Part emblemPart = request.getPart("emblem");

        if (heroIDParam == null || heroIDParam.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        int heroId = Integer.parseInt(heroIDParam);

        String images = null;
        if (filePart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            images = fileUploadHandler.handleFileUpload(filePart);
        } else {
            HeroDao heroDao = new HeroDao();
            Hero existingHero = heroDao.getById(heroId);
            images = existingHero.getImages();
        }

        String emblem = null;
        if (emblemPart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            emblem = fileUploadHandler.handleFileUpload(emblemPart);
        } else {
            HeroDao heroDao = new HeroDao();
            Hero existingHero = heroDao.getById(heroId);
            emblem = existingHero.getEmblem();
        }

        Hero updatedHero = new Hero();
        updatedHero.setHeroId(heroId);
        updatedHero.setCodeName(codeName);
        updatedHero.setRealName(realName);
        updatedHero.setBio(bio);
        updatedHero.setAlignment(alignment);
        updatedHero.setDescriptions(descriptions);
        updatedHero.setPersonality(personality);
        updatedHero.setHeight(height);
        updatedHero.setWeight(weight);
        updatedHero.setImages(images);
        updatedHero.setEmblem(emblem);

        HeroDao heroDao = new HeroDao();
        boolean success = heroDao.update(updatedHero);

        request.setAttribute("success", success);
        request.setAttribute("editedItemId", heroId);
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
