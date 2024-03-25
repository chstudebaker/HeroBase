<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Item Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<c:if test="${success}">
    <h1>Delete Successful</h1>
    <p>The item was successfully deleted.</p>
    <form action="${pageContext.request.contextPath}/generateWiki" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="heroId" value="${deletedItemId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return to Wiki">
    </form>
    <form action="${pageContext.request.contextPath}/heroList" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return Home">
    </form>
</c:if>
<c:if test="${!success}">
    <h1>Delete Failed</h1>
    <p>Failed to delete item. Please try again.</p>
    <form action="${pageContext.request.contextPath}/heroList" method="get">
        <!-- Include userId in the URL -->
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return Home">
    </form>
</c:if>
</body>
</html>
