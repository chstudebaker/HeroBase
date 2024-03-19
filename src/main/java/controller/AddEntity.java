package controller;

import entity.Blog;
import entity.Hero;
import entity.Powers;
import entity.Equipment;
import persistance.BlogDao;
import persistance.HeroDao;
import persistance.PowersDao;
import persistance.EquipmentDao;
import util.FileUploadHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddEntity")
@MultipartConfig
public class AddEntity extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddEntity.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");
        String userID = request.getParameter("userId");

        if (userID != null && !userID.isEmpty()) {
            if ("hero".equals(entityType)) {
                logger.log(Level.INFO, "Forwarding to addHero.jsp");
                request.getRequestDispatcher("addHero.jsp").forward(request, response);
            } else if ("power".equals(entityType)) {
                // Retrieve power descriptions from the database
                PowersDao powersDao = new PowersDao();
                Set<String> powerDescriptions = new HashSet<>(powersDao.getAllDescriptions());

                // Set power descriptions in request attributes
                request.setAttribute("powerDescriptions", powerDescriptions);

                // Forward the request to the JSP
                request.getRequestDispatcher("addPower.jsp").forward(request, response);
            } else if ("equipment".equals(entityType)) {
                logger.log(Level.INFO, "Forwarding to addEquipment.jsp");
                request.getRequestDispatcher("addEquipment.jsp").forward(request, response);
            } else if ("blog".equals(entityType)) {
                // Forward to addBlog.jsp
                logger.log(Level.INFO, "Forwarding to addBlog.jsp");
                request.getRequestDispatcher("addBlog.jsp").forward(request, response);
            } else {
                // Handle invalid or missing entity type
                logger.log(Level.WARNING, "Invalid entity type: " + entityType);
                response.sendRedirect("error.jsp");
            }
        } else {
            // Redirect to an error page or display a message indicating lack of permissions
            response.sendRedirect("only_users.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entityType = request.getParameter("type");


            if ("hero".equals(entityType)) {
                // Handle addition of a hero
                logger.log(Level.INFO, "Adding a hero");
                addHero(request, response);
            } else if ("power".equals(entityType)) {
                // Handle addition of a power
                logger.log(Level.INFO, "Adding a power");
                addPower(request, response);
            } else if ("equipment".equals(entityType)) {
                // Handle addition of equipment
                logger.log(Level.INFO, "Adding equipment");
                addEquipment(request, response);
            } else if ("blog".equals(entityType)) {
                // Handle addition of a blog
                logger.log(Level.INFO, "Adding a blog");
                addBlog(request, response);
            } else {
                // Handle invalid or missing entity type
                logger.log(Level.WARNING, "Invalid entity type: " + entityType);
                response.sendRedirect("error.jsp");
            }

    }

    private void addHero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");
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

        // Process file uploads separately
        FileUploadHandler fileUploadHandler = new FileUploadHandler();
        String images = fileUploadHandler.handleFileUpload(filePart);
        String emblem = fileUploadHandler.handleFileUpload(emblemPart);

        // Assuming you have a HeroDao for database operations
        HeroDao heroDao = new HeroDao();

        // Create a Hero object
        Hero hero = new Hero(codeName, realName, bio, alignment, images, descriptions, personality, height, weight, emblem);
        // Insert the hero into the database
        Integer insertedHeroId = heroDao.insert(hero);

        boolean success = insertedHeroId != null && insertedHeroId > 0;
        // Check if the hero was successfully inserted
        request.setAttribute("success", success);

        request.setAttribute("addedItemId", insertedHeroId);
        // Forward the request to the JSP
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }

    private void addPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");
        String heroID = request.getParameter("heroID");
        String selectedPower = request.getParameter("selectedPower");
        String customPower = request.getParameter("customPower");
        String explanation = request.getParameter("explanation");

        // Create a new instance of Powers entity
        Powers power = new Powers();
        power.setDescription(selectedPower != null && !selectedPower.isEmpty() ? selectedPower : customPower);
        power.setExplanation(explanation); // Set the explanation field

        // Set the HeroID on the Powers entity
        if (heroID != null && !heroID.isEmpty()) {
            power.setHeroID(Integer.parseInt(heroID));
        }

        PowersDao powersDao = new PowersDao();

        // Save the Powers entity
        Integer insertedPowerId = powersDao.insert(power);
        boolean success = insertedPowerId != null && insertedPowerId > 0;
        request.setAttribute("addedItemId", heroID);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }

    private void addEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");
        String heroID = request.getParameter("heroID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("images");

        // Handle file upload
        String images = null;
        if (filePart != null && filePart.getSize() > 0) {
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            images = fileUploadHandler.handleFileUpload(filePart);
        }

        // Create a new instance of Equipment entity
        Equipment equipment = new Equipment();
        equipment.setName(name); // Set the equipment name
        equipment.setDescription(description); // Set the equipment description
        equipment.setImages(images); // Set the file path to the Equipment entity

        // Set the hero ID on the Equipment entity
        if (heroID != null && !heroID.isEmpty()) {
            equipment.setHeroID(Integer.parseInt(heroID));
        }

        // Assuming you have an EquipmentDao for equipment-related operations
        EquipmentDao equipmentDao = new EquipmentDao();

        // Save the Equipment entity
        Integer insertedEquipmentId = equipmentDao.insert(equipment);

        // Check if the equipment was successfully inserted
        boolean success = insertedEquipmentId != null && insertedEquipmentId > 0;
        request.setAttribute("addedItemId", heroID);

        // Set the success attribute in the request
        request.setAttribute("success", success);

        // Forward the request to the JSP
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);
    }

    private void addBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userId");
        String heroID = request.getParameter("heroID");
        String blogTitle = request.getParameter("blogTitle");
        String blogContent = request.getParameter("blogContent");
        // You may include code to handle date and time if needed

        // Create a new instance of Blog entity
        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        blog.setBlogContent(blogContent);
        // Set date and time if needed

        if (heroID != null && !heroID.isEmpty()) {
            blog.setHeroID(Integer.parseInt(heroID));
        }
        BlogDao blogDao = new BlogDao();

        // Save the Blog entity
        Integer insertedBlogId = blogDao.insert(blog);
        boolean success = insertedBlogId != null && insertedBlogId > 0;

        // Set the success attribute in the request
        request.setAttribute("success", success);

        request.setAttribute("addedItemId", heroID);

        // Forward the request to the JSP
        request.getRequestDispatcher("addItemResult.jsp?userId=" + userID).forward(request, response);

    }
}
