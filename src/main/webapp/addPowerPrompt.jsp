<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>More To Add</title>
    <link rel="stylesheet" href="css/heroBase.css">

</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Hero Added Successfully!</h1>
<p>What would you like to do next?</p>
<form action="AddEntity" method="get">
    <input type="hidden" name="type" value="hero">
    <input type="hidden" name="heroId" value="${heroId}">
    <button type="submit">Add Another Hero</button>
</form>

<form action="AddEntity" method="get">
    <input type="hidden" name="type" value="power">
    <input type="hidden" name="heroId" value="${heroId}">
    <button type="submit">Add Power</button>
</form>

<form action="AddEntity" method="get">
    <input type="hidden" name="type" value="equipment">
    <input type="hidden" name="heroId" value="${heroId}">
    <button type="submit">Add Equipment</button>
</form>

<form action="${pageContext.request.contextPath}/" method="get">
    <button type="submit">Return Home</button>
</form>
</body>
</html>
