package com.chstudebaker.herobase.controller;

import com.chstudebaker.herobase.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/logIn"},
        loadOnStartup = 1 // Specify loadOnStartup to ensure this servlet is initialized on application startup
)
public class LogIn extends HttpServlet implements PropertiesLoader {
    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        loadProperties();
        setPropertiesInApplicationScope();
    }

    private void setPropertiesInApplicationScope() {
        getServletContext().setAttribute("CLIENT_ID", CLIENT_ID);
        getServletContext().setAttribute("LOGIN_URL", LOGIN_URL);
        getServletContext().setAttribute("REDIRECT_URL", REDIRECT_URL);
    }

    private void loadProperties() {
        try {
            properties = loadProperties("/cognito.properties");
            CLIENT_ID = properties.getProperty("client.id");
            LOGIN_URL = properties.getProperty("loginURL");
            REDIRECT_URL = properties.getProperty("redirectURL");
        } catch (IOException ioException) {
            logger.error("Cannot load properties..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO if properties weren't loaded properly, route to an error page
        if (CLIENT_ID == null || LOGIN_URL == null || REDIRECT_URL == null) {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } else {
            String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
            resp.sendRedirect(url);
        }
    }
}
