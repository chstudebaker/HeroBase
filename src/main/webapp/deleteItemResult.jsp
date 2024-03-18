<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Item Result</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<c:if test="${success}">
    <h1>Delete Successful</h1>
    <p>The item was successfully deleted.</p>
    <form action="generateWiki" method="get">
        <input type="hidden" name="heroId" value="${deletedItemId}">
        <input type="submit" value="Return to Wiki">
    </form>
    <form action="heroList" method="get">
        <input type="submit" value="Return Home">
    </form>
</c:if>
<c:if test="${!success}">
    <h1>Delete Failed</h1>
    <p>Failed to delete item. Please try again.</p>
    <form action="heroList" method="get">
        <input type="submit" value="Return Home">
    </form>
</c:if>
</body>
</html>
