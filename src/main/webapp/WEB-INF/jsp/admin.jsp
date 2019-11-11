<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>

<div>Welcome <c:out value= "${name}"/>!</div>
<c:choose>
    <c:when test="${isAdmin == 1}">
        <a href="/team/find">Admin Page1</a> <br />
        <a href="/team/create">Admin Page2</a>
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