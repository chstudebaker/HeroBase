<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logging out</title>
    <link rel="stylesheet" href="css/heroBase.css">

</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Logged Out</h1>
<h2>You have successfully logged out. See you next time!</h2>
<form action="heroList" method="get">
    <input type="submit" value="Return Home">
</form>
</body>
</html>
