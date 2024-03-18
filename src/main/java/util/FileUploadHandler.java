package util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadHandler {
    public String handleFileUpload(Part filePart) throws IOException {
        if (filePart == null) {
            throw new IOException("No file uploaded");
        }

        String fileName = getFileName(filePart);

        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Unable to retrieve file name from Content-Disposition header");
        }

        String relativePath = "images/" + fileName; // Relative path within the application
        String uploadDirectory = "C:/Users/cstudebaker/IdeaProjects/untitled/src/main/webapp/images"; // Absolute path to the images directory

        File uploadDir = new File(uploadDirectory);

        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new IOException("Failed to create upload directory: " + uploadDirectory);
            }
        } else {
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
