<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<c:if test="${success}">
    <h1>Edit Successful</h1>
    <p>The item was successfully edited.</p>
    <!-- Button to return to the wiki.jsp associated with the edited item -->
    <form action="${pageContext.request.contextPath}/generateWiki" method="get">
        <input type="hidden" name="heroId" value="${editedItemId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return to Wiki">
    </form>
    <!-- Button to return home -->
    <form action="${pageContext.request.contextPath}/heroList" method="get">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return Home">
    </form>
</c:if>
<c:if test="${!success}">
    <h1>Edit Failed</h1>
    <p>Failed to edit item. Please try again.</p>
    <form action="${pageContext.request.contextPath}/generateWiki" method="get">
        <input type="hidden" name="heroId" value="${editedItemId}">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return to Wiki">
    </form>
    <!-- Button to return home -->
    <form action="${pageContext.request.contextPath}/heroList" method="get">
        <input type="hidden" name="userId" value="${param.userId}">
        <input type="submit" value="Return Home">
    </form>
</c:if>
</body>
</html>
