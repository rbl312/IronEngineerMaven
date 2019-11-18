<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<h2> Team names to approve!</h2>
<c:forEach items="${approveTeams}" var="team">
    <form action="/admin/approve/${team.teamId}">
        <c:out value= "${team.teamName}" />
        <button class = "button">Approve this team name</button>
    </form>
    </br>
</c:forEach>
</body>
</html>