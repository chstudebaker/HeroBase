<%@ page import="java.util.*" %>
<%@ page import="User" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>

<jsp:include page="nav.jsp" />

<h2>Sign Up</h2>

<%
    // Retrieve existing users from the session or create a new list
    List<User> userList = (List<User>) session.getAttribute("userList");
    if (userList == null) {
        userList = new ArrayList<>();
        session.setAttribute("userList", userList);
    }

    // Handle sign-up form submission
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && password != null) {
        // Create a new user and add to the user list
        User newUser = new User(username, password);
        userList.add(newUser);
        out.println("<p>User signed up successfully!</p>");
    }
%>

<form action="SignUp.jsp" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Sign Up">
</form>

</body>
</html>
