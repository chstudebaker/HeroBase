<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Hero</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />

<h2>Add New Hero</h2>

<form action="${pageContext.request.contextPath}/AddHero?type=hero" method="post" enctype="multipart/form-data">
    <input type="hidden" name="userId" value="${param.userId}">
    <!-- Hero Name -->
    <label for="codeName">Code Name:</label>
    <input type="text" id="codeName" name="codeName" required><br>

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

    <!-- descriptions -->
    <label for="descriptions">Descriptions:</label>
    <textarea id="descriptions" name="descriptions" rows="4" cols="50"></textarea><br>

    <!-- personality -->
    <label for="personality">Personality:</label>
    <textarea id="personality" name="personality" rows="4" cols="50"></textarea><br>

    <!-- images Name -->
    <label for="images">Upload Image:</label>
    <input type="file" id="images" name="images" accept="image/*" required><br>

    <label for="height">Height:</label>
    <input type="text" id="height" name="height" required><br>

    <label for="weight">Code Name:</label>
    <input type="text" id="weight" name="weight" required><br>

    <label for="emblem">Upload Emblem:</label>
    <input type="file" id="emblem" name="emblem" accept="image/*" required><br>

    <!-- Submit Button -->
    <input type="submit" value="Add Hero">
</form>


</body>
</html>
