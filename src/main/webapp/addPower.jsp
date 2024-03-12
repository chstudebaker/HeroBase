<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Powers</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h2>Add Powers to Hero</h2>

<form action="AddPowers" method="post">

    <label for="heroID">Hero ID:</label>
    <input type="text" id="heroID" name="heroID" value="${param.heroId}" required>
    <br>

    <!-- Powers (Radio Buttons) -->
    <label>Powers:</label><br>
    <c:forEach var="power" items="${powerDescriptions}">
    <input type="radio" name="selectedPower" value="${power}">${power}<br>
    </c:forEach><br>

    <!-- Add Power Form Redirect -->
    <label for="addPowerRedirect">Add New Power:</label>
    <input type="text" id="addPowerRedirect" name="customPower">
    <small>(Enter new power name or leave empty)</small><br>

    <!-- Submit Button -->
    <input type="submit" value="Add Power">

</body>
</html>
