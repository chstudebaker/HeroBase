<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Equipment</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<h1>Edit Equipment</h1>
<form action="${pageContext.request.contextPath}/EditEquipment?userId=${param.userId}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="equipmentId" value="${equipment.equipmentId}">
    <input type="hidden" id="heroId" name="heroId" value="${hero.heroId}">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${equipment.name}" required><br>
    <label for="description">Description:</label><br>
    <textarea id="description" name="description" required>${equipment.description}</textarea><br>
    <label for="images">Image:</label>
    <input type="file" id="images" name="images" accept="image/*"><br> <!-- File input for image upload -->
    <input type="hidden" name="currentImage" value="${equipment.images}"> <!-- Hidden input for current image name -->
    <button type="submit">Save Changes</button>
</form>
</body>
</html>
