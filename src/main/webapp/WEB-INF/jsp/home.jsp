<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>

    <div class = "maintext">Welcome <c:out value= "${name}"/>!
    <c:choose>
        <c:when test="${not isOnTeam}">
            <a href="/team/find">Join a team</a> <br />
            <a href="/team/create">Create a team</a>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${isApproved != 1}">
                    Team: <c:out value = "${userTeam.teamName}"/>
                </c:when>
                <c:otherwise>
                    </br>
                    Team: Name waiting admin approval
                </c:otherwise>
            </c:choose>
            </br>
            Distance Swam: <c:out value = "${userTeam.distanceSwam}"/> </br>
            Distance Biked: <c:out value = "${userTeam.distanceBiked}"/> </br>
            Distance Ran: <c:out value = "${userTeam.distanceRan}"/> </br>
            <form action="/log">
                <button class = "button">Enter new race log</button>
            </form>
        </c:otherwise>
    </c:choose>
    </div>
</body>
</html>