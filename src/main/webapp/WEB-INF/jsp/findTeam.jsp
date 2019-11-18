<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
    <h2>Open Teams!</h2>
    <c:forEach items="${joinableTeams}" var="team">
        <form action="/team/join/${team.teamId}">
            <c:out value= "${team.teamName}" />
            <button class = "button">Join this team</button>
        </form>
        </div>
    </c:forEach>
</body>
</html>