<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>

<div>Welcome <c:out value= "${name}"/>!</div>
        You are a new contestant! Do you want to join a team? Or create a team?
        </br>

        <form action="/team/find">
            <button class = "button">Join a team</button>
        </form>
        <form action="/team/create">
            <button class = "button">Create a team</button>
        </form>
</body>
</html>