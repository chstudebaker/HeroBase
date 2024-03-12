<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Power Prompt</title>
    <link rel="stylesheet" href="css/heroBase.css">

</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Hero Added Successfully!</h1>
<p>What would you like to do next?</p>

<form action="AddPowers" method="get">
    <input type="hidden" name="heroId" value="${heroId}">
    <button type="submit">Add Power</button>
</form>

<form action="${pageContext.request.contextPath}/" method="get">
    <button type="submit">Return Home</button>
</form>
</body>
</html>
