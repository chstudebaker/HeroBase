<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Equipment</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<h2>Add Equipment</h2>
<form action="${pageContext.request.contextPath}/AddEntity?type=equipment" method="post" enctype="multipart/form-data">
    <input type="hidden" name="userId" value="${param.userId}">
    <label for="heroID">Hero ID:</label>
    <input type="text" id="heroID" name="heroID" value="${param.heroId}" required>
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" required><br>
    <label for="description">Description:</label><br>
    <textarea id="description" name="description" required></textarea><br>
    <label for="images">Upload Image:</label>
    <input type="file" id="images" name="images" accept="image/*" required><br>
    <input type="submit" value="Add Equipment">

</form>
</body>
</html>
