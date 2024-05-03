<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HeroBase</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="javascript/pagination.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />

<div class="container">
    <h2>List of Heroes</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Code Name</th>
            <th>Alignment</th>
            <th>Real Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hero" items="${heroList}">
            <tr class="item">
                <td>
                    <img class="hero-icon" src="${pageContext.request.contextPath}/${hero.images}" alt="Hero Icon">
                    <a href="${pageContext.request.contextPath}/generateWiki?heroId=${hero.heroId}&userId=${param.userId}">${hero.codeName}</a>
                </td>
                <td>${hero.alignment}</td>
                <td>${hero.realName}</td>
                <td>
                    <div class="delete-wrapper">
                        <a href="${pageContext.request.contextPath}/deleteHero.jsp?heroId=${hero.heroId}&userId=${param.userId}">
                            <img class="trash-icon" src="${pageContext.request.contextPath}/images/trash.png" alt="Trash Can Icon">
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- Pagination controls -->
    <div id="pagination" class="pagination-container"></div>
</div>

</body>


</html>
