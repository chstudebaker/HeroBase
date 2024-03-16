<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="css/heroBase.css">

</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Error Adding Item</h1>

<form action="AddEntity?type=hero" method="get">
    <button type="submit">Return to Add Hero Form</button>
</form>
</body>
</html>
