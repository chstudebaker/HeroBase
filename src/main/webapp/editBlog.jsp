<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Blog</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />

<h2>Edit Blog</h2>

<form action="EditEntity?type=blog&userId=${param.userId}" method="post">
    <!-- Hidden input field for Blog ID -->
    <input type="hidden" id="blogId" name="blogId" value="${blog.blogId}">

    <!-- Blog Title -->
    <label for="blogTitle">Blog Title:</label>
    <input type="text" id="blogTitle" name="blogTitle" value="${blog.blogTitle}" required><br>

    <!-- Blog Content -->
    <label for="blogContent">Blog Content:</label>
    <textarea id="blogContent" name="blogContent" rows="8" cols="50" required>${blog.blogContent}</textarea><br>

    <!-- Date & Time (if not auto-generated) -->
    <!-- <label for="dateTime">Date & Time:</label>
    <input type="datetime-local" id="dateTime" name="dateTime" value="${blog.dateTime}" required><br> -->

    <!-- Submit Button -->
    <input type="submit" value="Update Blog">
</form>

</body>
</html>
