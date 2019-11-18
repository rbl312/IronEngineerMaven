<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<h2> All Team Members!</h2>
<c:forEach items="${viewTeamMembers}" var="competitor">
    <c:out value= "${competitor.name}" /> ran:<c:out value= "${competitor.distanceRan}" /> swam:<c:out value= "${competitor.distanceSwam}" /> biked:<c:out value= "${competitor.distanceBiked}" />
    <a href="/admin/team/remove/competitor/${competitor.competitorId}">Remove Team Member</a>
    </br>
</c:forEach>
    <a href="/admin/remove/team/${team.teamId}">Delete Team</a>

</body>
<a href="/home">Home Page</a>
</html>