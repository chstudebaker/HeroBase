/**
 * A utility class to handle file uploads.
 */
package com.chstudebaker.herobase.util;

import com.chstudebaker.herobase.persistance.HeroDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUploadHandler {

    private static final Logger logger = LogManager.getLogger(HeroDao.class);
    private String fileSaveUrl; // Property to store the absolute path URL

    public FileUploadHandler() {
        // Load the file save URL from the properties file
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("general.properties")) {
            properties.load(inputStream);
            fileSaveUrl = properties.getProperty("fileSaveUrl");
        } catch (IOException e) {
            logger.error("URL is null or empty");        }
    }

    /**
     * Handles the file upload process.
     *
     * @param filePart The Part object representing the uploaded file.
     * @return The relative path of the uploaded file.
     * @throws IOException If an I/O error occurs during file upload.
     */
    public String handleFileUpload(Part filePart) throws IOException {
        if (filePart == null) {
            throw new IOException("No file uploaded");
        }

        String fileName = getFileName(filePart);

        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Unable to retrieve file name from Content-Disposition header");
        }

        String relativePath = "images/" + fileName; // Relative path within the application
        String uploadDirectory = fileSaveUrl;

        File uploadDir = new File(uploadDirectory);

        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new IOException("Failed to create upload directory: " + uploadDirectory);
            }
        }

        String uploadedFilePath = uploadDirectory + File.separator + fileName;

        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream out = new FileOutputStream(uploadedFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw e; // Rethrow the exception
        }

        return relativePath; // Return the relative file path
    }

    /**
     * Extracts the file name from the Content-Disposition header of the Part object.
     *
     * @param part The Part object representing the uploaded file.
     * @return The file name.
     */
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        if (contentDisposition != null) {
            String[] tokens = contentDisposition.split(";");
            for (String token : tokens) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }

}
