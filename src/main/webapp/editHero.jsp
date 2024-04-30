<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Hero</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />

<h2>Edit Hero</h2>

<form action="${pageContext.request.contextPath}/EditHero" method="post" enctype="multipart/form-data">
    <input type="hidden" id="userId" name="userId" value="${param.userId}">
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

    <!-- Image -->
    <label for="images">Image:</label>
    <input type="file" id="images" name="images" accept="image/*">
    <!-- Label for current image -->
    <label id="imageLabel" class="current-image">${pageContext.request.contextPath}/${hero.images ? hero.images : 'Current Image'}</label><br>

    <!-- Current Hero Image -->
    <img src="${pageContext.request.contextPath}/${hero.images}" alt="Current Image" class="current-image">

    <!-- Height -->
    <label for="height">Height:</label>
    <input type="text" id="height" name="height" value="${hero.height}" required><br>

    <!-- Weight -->
    <label for="weight">Weight:</label>
    <input type="text" id="weight" name="weight" value="${hero.weight}"><br>

    <!-- Emblem -->
    <label for="emblem">Upload Emblem:</label>
    <input type="file" id="emblem" name="emblem" accept="image/*">
    <!-- Label for current emblem -->
    <label id="emblemLabel" class="current-image">${pageContext.request.contextPath}/${hero.emblem ? hero.emblem : 'Current Image'}</label><br>

    <!-- Current Emblem Image -->
    <img src="${pageContext.request.contextPath}/${hero.emblem}" alt="Current Image" class="current-image">

    <!-- Submit Button -->
    <input type="submit" value="Update Hero">

</form>

</body>
</html>
