<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hero Wiki</title>
    <link rel="stylesheet" href="css/heroBase.css">
    <link rel="stylesheet" href="css/infobox.css">
    <style>
        .container {
            display: flex;
            flex-direction: column;
        }

        .hero-info {
            display: flex;
            flex-direction: row;
            align-items: flex-start;
            margin-bottom: 20px;
        }

        .hero-bio {
            flex: 1 1 70%;
            margin-right: 20px; /* Adjust as needed */
        }

        .infobox {
            flex: 0 0 30%;
            align-self: flex-start;
        }

        .whitespace {
            flex: 1 1 auto;
        }

        .power {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<div class="container">
    <div class="hero-info">
        <div class="hero-bio">
            <h1><b>${hero.codeName}</b></h1>
            <p>${hero.bio}</p>
            <hr>
            <p><b>Descriptions:</b></p>
            <p>${hero.descriptions}</p>
            <hr>
            <p><b>Personality:</b></p>
            <p>${hero.personality}</p>
            <hr>
            <hr>
            <h2>Powers:</h2>
            <c:forEach var="power" items="${powers}">
                <div class="power">
                    <h3>${power.description}</h3>
                    <p>${power.explanation}</p>
                </div>
            </c:forEach>
        </div>
        <div class="infobox">
            <img src="${hero.images}" alt="Hero Image">
            <div class="info">
                <h2>Hero Details</h2>
                <c:choose>
                    <c:when test="${empty hero}">
                        <p>No hero information found.</p>
                    </c:when>
                    <c:otherwise>
                        <p><strong>Real Name:</strong> ${hero.realName}</p>
                        <p><strong>Alignment:</strong> ${hero.alignment}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="whitespace"></div>
</div>
<!-- Add an "Edit" button with the hero ID included in the URL -->
<a href="editHero?heroID=${hero.heroId}" class="btn btn-primary">Edit</a>
</body>
</html>
