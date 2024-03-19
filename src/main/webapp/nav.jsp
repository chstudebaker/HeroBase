<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/heroBase.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #333;
            overflow: hidden;
            position: relative;
        }

        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }

        .profile-info {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            right: 50px; /* Adjust the distance from the right side */
            display: none;
            color: white;
        }

        .profile-info span {
            margin-left: 5px;
        }

        .profile-icon-container {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            right: 10px;
            display: none;
        }

        .profile-icon {
            width: 30px; /* Adjust the width and height as needed */
            height: 30px;
            border-radius: 50%; /* Make the image circular */
        }

        nav.logged-in .profile-icon-container,
        nav.logged-in .profile-info {
            display: inline-block;
        }
    </style>
</head>
<body>

<nav class="<c:if test='${not empty param.userId}'>logged-in</c:if>">
    <a href="heroList?&userId=${param.userId}">Home</a>
    <a href="results.jsp?&userId=${param.userId}">Search</a>
    <a href="AddEntity?type=hero&userId=${param.userId}">Add Hero</a>
    <c:choose>
        <c:when test="${empty param.userId}">
            <a href="logIn">Log In</a>
        </c:when>
        <c:otherwise>
            <div class="profile-icon-container">
                <img class="profile-icon" src="images/user_icon.png" alt="User Icon">
            </div>
            <div class="profile-info">
                <span>${param.userId}</span>
            </div>
            <a href="logout">Log Out</a>
        </c:otherwise>
    </c:choose>
</nav>
</body>
</html>
