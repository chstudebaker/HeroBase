<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hero Website</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />

<h1>Welcome to the Hero Website!</h1>
<c:choose>
    <c:when test="${empty userName}">
        <p>Just want to browse? Feel free to use this site. However, members enjoy additional perks! Feel free to create an account using the log in link above!</p>
    </c:when>
    <c:otherwise>
        <h3>Welcome ${userName}</h3>
    </c:otherwise>
</c:choose>

<div class="container">
    <h2>List of Heroes</h2>

    <!-- Display Hero List -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Code Name</th>
            <th>Alignment</th>
            <th>Real Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hero" items="${heroList}">
            <tr>
                <td><a href="wiki/${hero.codeName}.jsp">${hero.codeName}</a></td>
                <td>${hero.alignment}</td>
                <td>${hero.realName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
