<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HeroBase</title>
    <link rel="stylesheet" href="css/heroBase.css">
    <style>
        /* Style for hero icon */
        .hero-icon {
            width: 50px; /* Adjust width as needed */
            height: 50px; /* Make the icon circular */
            border-radius: 50%; /* Make the icon circular */
        }
    </style>
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Welcome to HeroBase</h1>
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

    <table class="table table-striped">
        <thead>
        <tr>
            <th></th> <!-- Empty header for hero icon -->
            <th>Code Name</th>
            <th>Alignment</th>
            <th>Real Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hero" items="${heroList}">
            <tr>
                <td><img class="hero-icon" src="${hero.images}" alt="Hero Icon">   <a href="generateWiki?heroId=${hero.heroId}">${hero.codeName}</a></td>
                <td>${hero.alignment}</td>
                <td>${hero.realName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
