<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<h2> All Teams!</h2>
<c:forEach items="${viewTeam}" var="team">
    <form action="/admin/team/view/${team.teamId}">
        Name: <c:out value= "${team.teamName}" />
        Approved: <c:out value="${team.approved}"/>
        <button class = "button">View this team</button>
    </form>
    </br>
</c:forEach>
</body>
</html>