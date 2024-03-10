<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp"%>

<html>
<head>
    <title>Hero Search Results</title>
</head>
<body>

<div class="container">
    <h2>Hero Search Results</h2>

    <!-- Hero Search Form -->
    <form action="searchHero" method="post">
        <label for="searchTerm">Search Term:</label>
        <input type="text" id="searchTerm" name="searchTerm" required>

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
            <th>Powers</th>
            <th>Alignment</th>
            <th>Real Name</th>
            <th>Bio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hero" items="${heroes}">
            <tr>
                <td>${hero.codeName}</td>
                <td>${hero.getPowersAsString()}</td>
                <td>${hero.alignment}</td>
                <td>${hero.realName}</td>
                <td>${hero.bio}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
