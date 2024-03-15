<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Delete Confirmation</title>
  <link rel="stylesheet" href="css/delete.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<div class="container">
  <h2>Are you sure you want to delete?</h2>
  <div class="button-wrapper">
    <a href="DeleteHero?heroId=${param.heroId}" class="yes">Yes</a>
    <a href="heroList" class="no">No</a>
  </div>
</div>
</body>
</html>
