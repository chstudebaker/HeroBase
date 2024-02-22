<%@include file="head.jsp"%>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/herobase.css">
    </head>
    <body>

    <h2>HEROBASE INDEX</h2>

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
    </body>
</html>