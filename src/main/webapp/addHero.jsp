<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Add Hero</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h2>Add New Hero</h2>

<form action="AddHeroServlet" method="post">
    <!-- Hero Name -->
    <label for="heroName">Hero Name:</label>
    <input type="text" id="heroName" name="heroName" required><br>

    <!-- Real Name -->
    <label for="realName">Real Name:</label>
    <input type="text" id="realName" name="realName" required><br>

    <!-- Bio -->
    <label for="bio">Bio:</label>
    <textarea id="bio" name="bio" rows="4" cols="50"></textarea><br>

    <!-- Alignment -->
    <label for="alignment">Alignment:</label>
    <select id="alignment" name="alignment">
        <option value="Good">Good</option>
        <option value="Evil">Evil</option>
        <option value="Anti-Hero">Anti-Hero</option>
        <option value="Other">Other</option>
    </select><br>

    <!-- Powers (Checkboxes) -->
    <label>Powers:</label><br>
    <!-- Add your powers dynamically based on the existing powers in the database -->
    <%-- Assuming powersList is a List<String> containing existing powers --%>
    <c:forEach var="power" items="${powersList}">
        <input type="checkbox" name="powers" value="${power}">${power}
        <%-- Add line break after every 4 checkboxes --%>
        <c:if test="${(status.index + 1) % 4 == 0}"><br></c:if>
    </c:forEach><br>

    <!-- Add Power Form Redirect -->
    <label for="addPowerRedirect">Add New Power:</label>
    <input type="text" id="addPowerRedirect" name="addPowerRedirect">
    <small>(Enter new power name or leave empty)</small><br>

    <!-- Submit Button -->
    <input type="submit" value="Add Hero">
</form>
</body>
</html>