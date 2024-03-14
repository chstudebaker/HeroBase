<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hero Wiki</title>
    <link rel="stylesheet" href="css/heroBase.css">
    <link rel="stylesheet" href="css/infobox.css">
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
                    <ul>
                        <li>${power.explanation}</li>
                    </ul>
                </div>
            </c:forEach>
        </div>
        <div class="infobox">
            <img src="${hero.images}" alt="Hero Image">
            <table class="info-table">
                <tr>
                    <th>Attribute</th>
                    <th>Value</th>
                </tr>
                <tr>
                    <td>Real Name:</td>
                    <td>${hero.realName}</td>
                </tr>
                <tr>
                    <td>Alignment:</td>
                    <td>${hero.alignment}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="whitespace"></div>
</div>
<!-- Add an "Edit" button with the hero ID included in the URL -->
<a href="editHero?heroID=${hero.heroId}" class="btn btn-primary">Edit</a>
</body>
</html>
