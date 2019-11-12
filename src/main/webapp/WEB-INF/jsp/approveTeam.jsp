<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>
<h2> Team names to approve!</h2>
<c:forEach items="${approveTeams}" var="team">
    <c:out value= "${team.teamName}" /> <a href="/admin/approve/${team.teamId}">Approve this team name</a>
    </br>
</c:forEach>
</body>
</html>