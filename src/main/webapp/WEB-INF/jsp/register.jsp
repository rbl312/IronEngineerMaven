<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="bootstrap.css" rel="stylesheet">
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
    <div class="jumbotron text-center">
            Welcome <c:out value= "${name}"/>!
            </br>
            You are a new contestant! Do you want to join a team? Or create a team?
    </div>
    <div class="row text-center">
        <div class="col">
            <form action="/team/find">
                <button class="button">Join a team</button>
            </form>
        </div>
    </div>
    <div class="row text-center">
        <div class="col">
            <form action="/team/create">
                <button class="button">Create a team</button>
            </form>
        </div>
    </div>
</body>
</html>