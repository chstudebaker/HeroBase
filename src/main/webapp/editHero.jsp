<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Hero</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />

<h2>Edit Hero</h2>

<form action="editHero" method="post">
    <!-- Hidden input field for heroID -->
    <input type="hidden" id="heroId" name="heroId" value="${hero.heroId}">

    <!-- Hero Name -->
    <label for="codeName">Hero Name:</label>
    <input type="text" id="codeName" name="codeName" value="${hero.codeName}" required><br>

    <!-- Real Name -->
    <label for="realName">Real Name:</label>
    <input type="text" id="realName" name="realName" value="${hero.realName}" required><br>

    <!-- Bio -->
    <label for="bio">Bio:</label>
    <textarea id="bio" name="bio" rows="4" cols="50">${hero.bio}</textarea><br>

    <!-- Alignment -->
    <label for="alignment">Alignment:</label>
    <select id="alignment" name="alignment">
        <option value="Good" ${hero.alignment eq 'Good' ? 'selected' : ''}>Good</option>
        <option value="Evil" ${hero.alignment eq 'Evil' ? 'selected' : ''}>Evil</option>
        <option value="Anti-Hero" ${hero.alignment eq 'Anti-Hero' ? 'selected' : ''}>Anti-Hero</option>
        <option value="Other" ${hero.alignment eq 'Other' ? 'selected' : ''}>Other</option>
    </select><br>

    <!-- Descriptions -->
    <label for="descriptions">Descriptions:</label>
    <textarea id="descriptions" name="descriptions" rows="4" cols="50">${hero.descriptions}</textarea><br>

    <!-- Personality -->
    <label for="personality">Personality:</label>
    <textarea id="personality" name="personality" rows="4" cols="50">${hero.personality}</textarea><br>

    <!-- Submit Button -->
    <input type="submit" value="Update Hero">

</form>
</body>
</html>
