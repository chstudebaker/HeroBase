<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Hero</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h2>Add New Hero</h2>

<form action="AddHero" method="post">
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

    <!-- Add Power Button -->
    <label for="powerPopup">Add Powers</label>
    <input type="checkbox" id="powerPopup" style="display: none;">
    <div id="powerList" style="max-height: 200px; overflow-y: scroll;">
        <!-- Populate with checkbox list dynamically -->
        <c:forEach var="power" items="${powersList}">
            <input type="checkbox" name="powers" value="${power.description}">${power.description}<br>
        </c:forEach>
    </div>
    <!-- Submit Button -->
    <input type="submit" value="Add Hero"> <!-- Updated the value to reflect the action -->

</form>
</body>
</html>
