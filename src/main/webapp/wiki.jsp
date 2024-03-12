<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hero Wiki</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />

<div class="container">
    <h2>Hero Details</h2>

    <c:choose>
        <c:when test="${empty hero}">
            <p>No hero information found.</p>
        </c:when>
        <c:otherwise>
            <h3>${hero.codeName}</h3>
            <p><strong>Real Name:</strong> ${hero.realName}</p>
            <p><strong>Alignment:</strong> ${hero.alignment}</p>
            <!-- Add more details as needed -->
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
