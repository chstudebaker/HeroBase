<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Powers Result</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Power Addition Successful</h1>
<p>The power was successfully added.</p>
<form action="AddHero" method="get">
    <input type="submit" value="Add Another Hero">
</form>
<form action="AddPowers" method="get">
    <input type="submit" value="Add Another Power">
</form>
<form action="${pageContext.request.contextPath}/" method="get">
    <input type="submit" value="Return Home">
</form>
</body>
</html>
