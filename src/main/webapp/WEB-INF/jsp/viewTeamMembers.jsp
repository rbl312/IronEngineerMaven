<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
    <h2> Team members for <c:out value = "${team.teamName}" /></h2>
    <c:forEach items="${team.competitors}" var="competitor">
        <form action="/admin/team/remove/competitor/${competitor.competitorId}">
            <c:out value= "${competitor.name}" />
            Ran:<c:out value= "${competitor.distanceRan}" />
            Swam:<c:out value= "${competitor.distanceSwam}" />
            Biked:<c:out value= "${competitor.distanceBiked}" />
            <button class = "button">Remove this member</button>
        </form>
        </br>
    </c:forEach>

    <form action="/admin/remove/team/${team.teamId}">
        <button class = "button">Remove this team</button>
    </form>
</body>
</html>