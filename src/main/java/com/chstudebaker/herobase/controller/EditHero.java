package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.entity.Hero;
import com.chstudebaker.herobase.persistance.HeroDao;
import com.chstudebaker.herobase.util.FileUploadHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@MultipartConfig
@WebServlet("/EditHero")
public class EditHero extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(EditHero.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String heroIDParam = request.getParameter("heroId");
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            if (heroIDParam == null || heroIDParam.isEmpty()) {
                logger.error("HeroID is null or empty");
                response.sendRedirect("error400.jsp");
                return;
            }

            int heroId = Integer.parseInt(heroIDParam);
            logger.info("HeroID: " + heroId);

            HeroDao heroDao = new HeroDao();
            Hero hero = heroDao.getById(heroId);

            if (hero == null) {
                logger.warn("No hero found for the provided ID: " + heroId);
                response.sendRedirect("error500.jsp");
                return;
            }

            request.setAttribute("hero", hero);
            request.getRequestDispatcher("editHero.jsp").forward(request, response);
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(request.getParameter("heroId"));
        editHero(request, response);

    }

    private void editHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        logger.info("POST Request received. HeroIDParam: " + heroIDParam);


        logger.info("Code Name: " + codeName);
        logger.info("Real Name: " + realName);
        logger.info("Bio: " + bio);
        logger.info("Alignment: " + alignment);
        logger.info("Descriptions: " + descriptions);
        logger.info("Personality: " + personality);
        logger.info("Height: " + height);
        logger.info("Weight: " + weight);

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

        HeroDao heroDao = new HeroDao();
        Hero updatedHero = heroDao.getById(heroId);
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

        logger.info("Updated Hero: " + updatedHero);

        boolean success = heroDao.update(updatedHero);

        String userID = request.getParameter("userId");

        request.setAttribute("success", success);
        request.setAttribute("editedItemId", heroId);
        request.getRequestDispatcher("editItemResult.jsp?userId=" + userID).forward(request, response);
    }
}
