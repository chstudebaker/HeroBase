<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hero Website</title>
</head>
<body>

<h1>Welcome to the Hero Website!</h1>
<c:choose>
    <c:when test="${empty userName}">
        <a href = "logIn">Log in</a>
    </c:when>
    <c:otherwise>
        <h3>Welcome ${userName}</h3>
    </c:otherwise>
</c:choose>
<p>
    Explore the heroic world and <a href="searchHero">search for your favorite heroes</a>.
</p>


</body>
</html>
