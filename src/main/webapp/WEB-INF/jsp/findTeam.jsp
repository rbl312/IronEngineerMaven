<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>
    <h2>Open Teams!</h2>
    <c:forEach items="${joinableTeams}" var="team">
        <c:out value= "${team.name}" /> <a href="/team/join/${team.id}">Join this team</a>
        </br>
    </c:forEach>
</body>
</html>