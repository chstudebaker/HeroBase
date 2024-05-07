<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Power</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<h1>Edit Power</h1>
<form action="${pageContext.request.contextPath}/EditPower" method="POST">
  <input type="hidden" name="heroId" value="${power.hero.heroId}">
  <input type="hidden" name="powerID" value="${param.powerID}">
  <input type="hidden" name="userId" value="${param.userId}">
  <label for="heroID">Hero ID:</label>
  <input type="text" id="heroID" name="heroID" value="${param.heroId}" required>
  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description" value="${power.description}" required><br>
  <label for="explanation">Explanation:</label><br>
  <textarea id="explanation" name="explanation" required>${power.explanation}</textarea><br>
  <button type="submit">Save Changes</button>
</form>
</body>
</html>
