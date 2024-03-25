<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Powers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<h2>Add Powers to Hero</h2>

<form action="${pageContext.request.contextPath}/AddEntity?type=power" method="post">
    <input type="hidden" name="userId" value="${param.userId}">
    <label for="heroID">Hero ID:</label>
    <input type="text" id="heroID" name="heroID" value="${param.heroId}" required>
    <br>

    <label for="explanation">Explanation of power:</label>
    <input type="text" id="explanation" name="explanation">
    <br>

    <!-- Powers (Scrollable List) -->
    <label>Powers:</label><br>
    <div style="height: 150px; overflow-y: auto;">
        <c:forEach var="power" items="${powerDescriptions}">
            <input type="radio" name="selectedPower" value="${power}">${power}<br>
        </c:forEach>
    </div>
    <br>

    <!-- Add Power Form Redirect -->
    <label for="addPowerRedirect">Add New Power:</label>
    <input type="text" id="addPowerRedirect" name="customPower">
    <small>(Enter new power name or leave empty)</small><br>

    <!-- Submit Button -->
    <input type="submit" value="Add Power">

</form>
</body>
</html>
