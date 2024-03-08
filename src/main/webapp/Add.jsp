<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Hero</title>
</head>
<body>

<h1>Add a New Hero</h1>

<form action="addHero" method="post">
    <label for="codeName">Code Name:</label>
    <input type="text" id="codeName" name="codeName" required><br>

    <label for="powers">Powers:</label>
    <input type="text" id="powers" name="powers" required><br>

    <label for="bio">Biography:</label>
    <textarea id="bio" name="bio" rows="4" required></textarea><br>

    <label for="alignment">Alignment:</label>
    <input type="text" id="alignment" name="alignment" required><br>

    <label for="realName">Real Name:</label>
    <input type="text" id="realName" name="realName" required><br>

    <button type="submit">Add Hero</button>
</form>

<p>
    <a href="index.jsp">Back to Home</a>
</p>

</body>
</html>
