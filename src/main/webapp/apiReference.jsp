<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>API Reference - HeroBase</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<main>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="alert alert-primary" role="alert">
                    <h4 class="alert-heading"><a href="https://herobase101-env.eba-mwb3wpct.us-east-1.elasticbeanstalk.com/api/heroes/">GET /heroes</a></h4>
                    <p>Retrieves all heroes.</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="alert alert-primary" role="alert">
                    <h4 class="alert-heading"><a href="https://herobase101-env.eba-mwb3wpct.us-east-1.elasticbeanstalk.com/api/heroes/">{heroId} GET /heroes/{heroId}</a></h4>
                    <p>Retrieves a hero based on the given ID.</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="alert alert-success" role="alert">
                    <h4 class="alert-heading">POST /heroes</h4>
                    <p>Creates a new hero.</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="alert alert-danger" role="alert">
                    <h4 class="alert-heading">DELETE /heroes/{heroId}</h4>
                    <p>Deletes a hero based on the given ID.</p>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>
