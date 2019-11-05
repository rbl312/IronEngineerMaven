<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>

    <div>Welcome <c:out value= "${name}"/>!</div>
    <c:choose>
        <c:when test="${isOnTeam == false}">
            <a href="/team/find">Join a team</a> <br />
            <a href="/team/create">Create a team</a>
        </c:when>
        <c:otherwise>
            Team: <c:out value = "${userTeam.teamName}"/> </br>
            Distance Swam: <c:out value = "${userTeam.distanceSwam}"/> </br>
            Distance Biked: <c:out value = "${userTeam.distanceBiked}"/> </br>
            Distance Ran: <c:out value = "${userTeam.distanceRan}"/> </br>
            <a href="/log">Enter new race log</a>
        </c:otherwise>
    </c:choose>
</body>
</html>