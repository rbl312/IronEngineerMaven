<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>

<div>Welcome <c:out value= "${name}"/>! </br>You are on Admin page! </br> </div>
<c:choose>
    <c:when test="${isAdmin == 1}">
        <a href="/admin/approve">Approve team names</a> <br />
        <a href="/admin/team/view">View All Team Progress</a>
    </c:when>
    <c:otherwise>
        Team: <c:out value = "${userTeam.teamName}"/> </br>
        Distance Swam: <c:out value = "${userTeam.distanceSwam}"/> </br>
        Distance Biked: <c:out value = "${userTeam.distanceBiked}"/> </br>
        Distance Ran: <c:out value = "${userTeam.distanceRan}"/> </br>
        <form action="/log"">
            <button class = "button">Enter new race log</button>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>