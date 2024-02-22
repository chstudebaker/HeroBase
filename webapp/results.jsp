<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/herobase.css">
</head>
<body>

<div class="container-fluid">
    <h2>Search Results:</h2>

    <!-- Search Form -->
    <form class="search-form" action="${pageContext.request.contextPath}/searchHero" method="GET">
        <label for="codeName">Search by Code Name:</label>
        <input type="text" id="codeName" name="codeName">
        <button type="submit">Search</button>
    </form>
    <table>
        <thead>
        <tr>
            <th>Code Name</th>
            <th>Secret Identity</th>
            <th>Alignment</th>
            <th>Power Tags</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hero" items="${heroes}">
            <tr>
                <td>${hero.codeName}</td>
                <td>${hero.secretIdentity}</td>
                <td>${hero.alignment}</td>
                <td>${hero.powerTags}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
