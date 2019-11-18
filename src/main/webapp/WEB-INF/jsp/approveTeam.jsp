<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<a href="/admin">Home</a> <br />
<h2> Team names to approve!</h2>
<c:forEach items="${approveTeams}" var="team">
    <c:out value= "${team.teamName}" />
    <form action="/admin/approve/${team.teamId}"">
        <button class = "button">Approve this team name</button>
    </form>
    </br>
</c:forEach>

</body>
</html>