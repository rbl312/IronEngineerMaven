<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>

<div>Welcome <c:out value= "${name}"/>!</div>
        You are a new contestant! Do you want to join a team? or create a team? </br>
        <a href="/team/find">Join a team</a> <br />
        <a href="/team/create">Create a team</a>
</body>
</html>