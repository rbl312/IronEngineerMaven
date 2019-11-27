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
            <form action="/team/find">
                <button class = "button">Join a team</button>
            </form>
            <form action="/team/create">
                <button class = "button">Create a team</button>
            </form>
        </c:when>
        <c:otherwise>
            <form action="/logout">
                <button class = "button">Logout</button>
            </form>
            <c:choose>
                <c:when test="${isApproved != 1}">
                    Team: Name waiting admin approval
                </c:when>
                <c:otherwise>
                    </br>
                    Team: <c:out value = "${userTeam.teamName}"/>
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