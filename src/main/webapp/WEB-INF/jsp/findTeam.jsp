<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
    <h2>Open Teams!</h2>
    <c:forEach items="${joinableTeams}" var="team">
        <c:out value= "${team.teamName}" />
        <form action="/team/join/${team.teamId}">
                <button class = "button">Join this team</button>
        </form>
        </br>
    </c:forEach>
</body>
</html>