<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Blog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />

<h2>Add Blog</h2>

<form action="${pageContext.request.contextPath}/AddBlog" method="post">
    <input type="hidden" name="userId" value="${param.userId}">
    <input type="hidden" name="heroId" value="${blog.hero.heroId}">
    <label for="heroID">Hero ID:</label>
    <input type="text" id="heroID" name="heroID" value="${param.heroId}" required>
    <!-- Blog Title -->
    <label for="blogTitle">Blog Title:</label>
    <input type="text" id="blogTitle" name="blogTitle" required><br>

    <!-- Blog Content -->
    <label for="blogContent">Blog Content:</label>
    <textarea id="blogContent" name="blogContent" rows="8" cols="50" required></textarea><br>

    <!-- Date & Time (if not auto-generated) -->
    <label for="dateTime">Date & Time:</label>
    <input type="datetime-local" id="dateTime" name="dateTime" required><br>

    <!-- Submit Button -->
    <input type="submit" value="Add Blog">
</form>

</body>
</html>
