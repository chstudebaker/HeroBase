<%@ page import="User" %>
<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>

<jsp:include page="nav.jsp" />

<h2>Sign In</h2>

<%
    // Retrieve existing users from the session or create a new list
    List<User> userList = (List<User>) session.getAttribute("userList");
    if (userList == null) {
        userList = new ArrayList<>();
        session.setAttribute("userList", userList);
    }

    // Handle sign-in form submission
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && password != null) {
        // Check if the user exists
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                out.println("<p>Welcome, " + username + "!</p>");
                return;
            }
        }
        out.println("<p>Invalid username or password. Please try again.</p>");
    }
    out.println("<p>If you do not currently have an account, please <a href="SignUp.jsp"><strong>Sign Up</strong></a>.</p>")
%>

<form action="SignIn.jsp" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Sign In">
</form>

</body>
</html>
