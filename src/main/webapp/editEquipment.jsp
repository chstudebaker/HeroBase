<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Equipment</title>
    <link rel="stylesheet" href="css/heroBase.css"></head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Edit Equipment</h1>
<form action="EditEntity?type=equipment" method="POST">
    <input type="hidden" name="equipmentID" value="${param.equipmentID}">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${equipment.name}" required><br>
    <label for="description">Description:</label><br>
    <textarea id="description" name="description" required>${equipment.description}</textarea><br>
    <button type="submit">Save Changes</button>
</form>
</body>
</html>
