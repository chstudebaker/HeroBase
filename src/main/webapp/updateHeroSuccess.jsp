<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Hero Result</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Hero Update Successful</h1>
<p>The hero was successfully updated.</p>
<form action="${pageContext.request.contextPath}/" method="get">
    <input type="submit" value="Return Home">
</form>
</body>
</html>
