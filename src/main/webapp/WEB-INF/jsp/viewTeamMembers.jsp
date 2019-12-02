<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/bootstrap.css" rel="stylesheet">
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<div class= "row text-center">
    <div class = "col">
        <h2> Team members for <c:out value = "${team.teamName}" /></h2>
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
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
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
        <form action="/admin/remove/team/${team.teamId}">
            <button class = "button">Remove this team</button>
        </form>
    </div>
</div>
</body>
</html>