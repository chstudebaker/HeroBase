<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hero Search Results</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="javascript/pagination.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />

<div class="container">
    <h2>Hero Search Results</h2>

    <!-- Hero Search Form -->
    <form action="${pageContext.request.contextPath}/searchHero" method="post">
        <label for="searchTerm">Search Term:</label>
        <input type="text" id="searchTerm" name="searchTerm">

        <!-- Radio buttons for selecting search criteria -->
        <label><input type="radio" name="searchCriteria" value="codeName" checked> Code Name</label>
        <label><input type="radio" name="searchCriteria" value="alignment"> Alignment</label>
        <label><input type="radio" name="searchCriteria" value="realName"> Real Name</label>

        <button type="submit">Search</button>
    </form>
    <!-- Display Search Results -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Code Name</th>
            <th>Real Name</th>
            <th>Alignment</th>
            <th>Powers</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hero" items="${heroes}">
            <tr class="item">
                <td>
                    <img class="hero-icon" src="${pageContext.request.contextPath}/${hero.images}" alt="Hero Icon">
                    <a href="${pageContext.request.contextPath}/generateWiki?heroId=${hero.heroId}&userId=${param.userId}">${hero.codeName}</a>
                </td>
                <td>${hero.realName}</td>
                <td>${hero.alignment}</td>
                <td>${hero.getPowersAsString()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- Pagination controls -->
    <div id="pagination" class="pagination-container"></div>
</div>
</body>
</html>
