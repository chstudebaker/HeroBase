<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item Result</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<c:if test="${success}">
    <h1>Edit Successful</h1>
    <p>The item was successfully edited.</p>
    <!-- Button to return to the wiki.jsp associated with the edited item -->
    <form action="generateWiki" method="get">
        <input type="hidden" name="heroId" value="${editedItemId}">
        <input type="submit" value="Return to Wiki">
    </form>
    <!-- Button to return home -->
    <form action=" " method="get">
        <input type="submit" value="Return Home">
    </form>
</c:if>
<c:if test="${!success}">
    <h1>Edit Failed</h1>
    <p>Failed to edit item. Please try again.</p>
        <form action="generateWiki" method="get">
            <input type="hidden" name="heroId" value="${editedItemId}">
            <input type="submit" value="Return to Wiki">
        </form>
        <!-- Button to return home -->
        <form action=" " method="get">
            <input type="submit" value="Return Home">
        </form>
</c:if>
</body>
</html>
