package controller;

import entity.Hero;
import persistance.HeroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/wiki")
public class GenerateWiki extends HttpServlet {

    private static final Logger logger = Logger.getLogger(GenerateWiki.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve hero ID from the request parameter
        int heroId = Integer.parseInt(request.getParameter("heroId"));

        // Fetch hero details from the database
        HeroDao heroDao = new HeroDao();
        Hero hero = heroDao.getById(heroId);

        // Generate wiki-like page content
        String wikiContent = generateWikiContent(hero);

        // Set generated wiki content as request attribute
        request.setAttribute("wikiContent", wikiContent);

        // Forward the request to the wiki.jsp page to display the generated content
        request.getRequestDispatcher("wiki.jsp").forward(request, response);
    }

    private String generateWikiContent(Hero hero) {
        StringBuilder wikiContent = new StringBuilder();
        wikiContent.append("<h2>").append(hero.getCodeName()).append("</h2>");
        wikiContent.append("<p><strong>Real Name:</strong> ").append(hero.getRealName()).append("</p>");
        wikiContent.append("<p><strong>Alignment:</strong> ").append(hero.getAlignment()).append("</p>");
        wikiContent.append("<p><strong>Bio:</strong> ").append(hero.getBio()).append("</p>");

        return wikiContent.toString();
    }
}
