<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>

    <div>Welcome <c:out value= "${name}"/>!</div>
    <c:choose>
        <c:when test="${not isOnTeam}">
            <a href="/team/find">Join a team</a> <br />
            <a href="/team/create">Create a team</a>
        </c:when>
        <c:otherwise>
            Team: <c:out value = "${userTeam.teamName}"/>
            <c:choose>
                <c:when test="${isApproved != 1}">
                    </br>
                    <font color="red">    NAME NOT APPROVED</font>
                </c:when>
                <c:otherwise>
                    </br>
                    <font color="green">    NAME APPROVED</font>
                </c:otherwise>
            </c:choose>
            </br>

            Distance Swam: <c:out value = "${userTeam.distanceSwam}"/> </br>
            Distance Biked: <c:out value = "${userTeam.distanceBiked}"/> </br>
            Distance Ran: <c:out value = "${userTeam.distanceRan}"/> </br>
            <a href="/log">Enter new race log</a>
        </c:otherwise>
    </c:choose>
</body>
</html>