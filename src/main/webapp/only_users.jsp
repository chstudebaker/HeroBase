<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Only Users Page</title>
    <link rel="stylesheet" type="text/css" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />

<div class="container">
    <h1>Only Users Page</h1>
    <p>Sorry, this page is only accessible to logged-in users.</p>
    <p>Please <a href="logIn">log in</a> to access this page.</p>
</div>
</body>
</html>
