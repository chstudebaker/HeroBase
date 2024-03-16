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
    <form action="AddEntity" method="get">
        <input type="hidden" name="type" value="hero">
        <input type="hidden" name="heroId" value="${heroId}">
        <button type="submit">Add Power</button>
    </form>
    <form action="AddEntity" method="get">
        <input type="hidden" name="type" value="power">
        <input type="hidden" name="heroId" value="${heroId}">
        <button type="submit">Add Power</button>
    </form>
    <form action="AddEntity" method="get">
        <input type="hidden" name="type" value="equipment">
        <input type="hidden" name="heroId" value="${heroId}">
        <button type="submit">Add Equipment</button>
    </form>
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="Return Home">
    </form>
</c:if>
<c:if test="${!success}">
    <h1>Addition Failed</h1>
    <p>Failed to add item. Please try again.</p>
    <form action="AddEntity" method="get">
        <input type="hidden" name="type" value="hero">
        <input type="hidden" name="heroId" value="${heroId}">
        <button type="submit">Add A Hero</button>
    </form>
    <form action="AddEntity" method="get">
        <input type="hidden" name="type" value="power">
        <input type="hidden" name="heroId" value="${heroId}">
        <button type="submit">Try Again</button>
    </form>
    <form action="AddEntity" method="get">
        <input type="hidden" name="type" value="equipment">
        <input type="hidden" name="heroId" value="${heroId}">
        <button type="submit">Add Equipment</button>
    </form>
    <form action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="Return Home">
    </form>
</c:if>
</body>
</html>
