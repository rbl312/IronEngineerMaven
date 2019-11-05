<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>
    <h2>Open Teams!</h2>
    <c:forEach items="${joinableTeams}" var="team">
        <c:out value= "${team.teamName}" /> <a href="/team/join/${team.teamId}">Join this team</a>
        </br>
    </c:forEach>
</body>
</html>