<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hero Wiki</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/infobox.css">
    <script src="javascript/hideEmblem.js"></script>
    <style>
        .edit-delete-buttons {
            display: flex;
        }

        .power .edit-delete-buttons {
            display: none;
        }

        .power:hover .edit-delete-buttons {
            display: flex;
        }

        .edit-delete-buttons a {
            margin-right: 5px;
        }


        .edit-button-hero {
            position: fixed;
            top: 60px; /* Adjust as needed depending on your nav bar height */
            left: 10px;
            z-index: 1000; /* Ensure it stays above other elements */
        }


        .edit-button {
            background-color: #3498db; /* Blue color */
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }

        .delete-button {
            background-color: #e74c3c; /* Red color */
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<a href="${pageContext.request.contextPath}/EditHero?heroId=${hero.heroId}&userId=${param.userId}" class="btn btn-primary edit-button-hero">Edit</a>
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
                    <div class="edit-delete-buttons">
                        <a href="${pageContext.request.contextPath}/EditPower?powerID=${power.powerID}&userId=${param.userId}" class="edit-button">Edit</a>
                        <a href="${pageContext.request.contextPath}/DeletePower?powerID=${power.powerID}&userId=${param.userId}" class="delete-button">Delete</a>
                    </div>
                </div>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/AddPower?heroId=${hero.heroId}&userId=${param.userId}" class="edit-button">Add</a>
            <hr>
            <hr>
            <h2>Equipment:</h2>
            <c:forEach var="equipment" items="${equipment}">
                <div class="power">
                    <h3><img class="hero-icon" src="${equipment.images}" alt="Equipment Icon">${equipment.name}</h3>
                    <ul>
                        <li>${equipment.description}</li>
                    </ul>
                    <div class="edit-delete-buttons">
                        <a href="${pageContext.request.contextPath}/EditEquipment?equipmentId=${equipment.equipmentId}&userId=${param.userId}" class="edit-button">Edit</a>
                        <a href="${pageContext.request.contextPath}/DeleteEquipment?equipmentId=${equipment.equipmentId}&userId=${param.userId}" class="delete-button">Delete</a>
                    </div>
                </div>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/AddEquipment?heroId=${hero.heroId}&userId=${param.userId}" class="edit-button">Add</a>
            <!-- Add Blog Button -->
            <a href="${pageContext.request.contextPath}/AddBlog?heroId=${hero.heroId}&userId=${param.userId}" class="edit-button">Add Blog</a>

            <c:forEach var="blog" items="${blogs}">
                <div class="blog-container">
                    <div class="blog-title">${blog.blogTitle}</div>
                    <div class="blog-content">${blog.blogContent}</div>
                    <div class="blog-info">
                        <span class="blog-info-item">Date: ${blog.dateTime}</span>
                    </div>
                    <!-- Trash icon for deleting the blog -->
                    <div class="edit-delete-buttons">
                        <a href="${pageContext.request.contextPath}/EditBlog?blogId=${blog.blogId}&userId=${param.userId}" class="edit-button">Edit</a>
                        <div class="delete-wrapper">
                            <a href="${pageContext.request.contextPath}/DeleteBlog?blogId=${blog.blogId}&userId=${param.userId}">
                                <img class="trash-icon" src="${pageContext.request.contextPath}/images/trash.png" alt="Trash Can Icon">
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="infobox">
            <div class="hero-name-row">
                <p style="text-align: center; font-weight: bold;">${hero.codeName}</p>
            </div>
            <div class="hero-image-container">
                <img src="${hero.images}" alt="Hero Image">
            </div>
            <table class="info-table">
                <tr>
                    <td>Real Name:</td>
                    <td>${hero.realName}</td>
                </tr>
                <tr>
                    <td>Alignment:</td>
                    <td>${hero.alignment}</td>
                </tr>
                <tr>
                    <td>Height:</td>
                    <td>${hero.height}</td>
                </tr>
                <tr>
                    <td>Weight:</td>
                    <td>${hero.weight}</td>
                </tr>
            </table>
            <div class="emblem-container" id="emblemContainer">
                <p><strong>Emblem</strong></p>
                <img src="${hero.emblem}" alt="Hero Emblem" id="heroEmblem">
            </div>
        </div>

    </div>
    <div class="whitespace"></div>
</div>
</body>
</html>
