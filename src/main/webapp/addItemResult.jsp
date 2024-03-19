<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Item Result</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<c:if test="${success}">
    <h1>Addition Successful</h1>
    <p>The item was successfully added.</p>
    <form action="generateWiki" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="heroId" value="${addedItemId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return to Wiki">
    </form>
    <form action="AddEntity" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="type" value="hero">
        <input type="hidden" name="heroId" value="${heroId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Add a new Hero">
    </form>
    <form action="AddEntity" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="type" value="power">
        <input type="hidden" name="heroId" value="${heroId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Add a new Power">
    </form>
    <form action="AddEntity" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="type" value="equipment">
        <input type="hidden" name="heroId" value="${heroId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Add a new piece of Equipment">
    </form>
    <form action="heroList" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return Home">
    </form>
</c:if>
<c:if test="${!success}">
    <h1>Addition Failed</h1>
    <p>Failed to add item. Please try again.</p>
    <form action="AddEntity" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="type" value="hero">
        <input type="hidden" name="heroId" value="${heroId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <button type="submit">Add A Hero</button>
    </form>
    <form action="AddEntity" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="type" value="power">
        <input type="hidden" name="heroId" value="${heroId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <button type="submit">Try Again</button>
    </form>
    <form action="AddEntity" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="type" value="equipment">
        <input type="hidden" name="heroId" value="${heroId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <button type="submit">Add Equipment</button>
    </form>
    <form action="heroList" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return Home">
    </form>
</c:if>
</body>
</html>
