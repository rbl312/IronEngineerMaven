<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>
<a href="/admin">Home</a> <br />
<h2> All Team Members!</h2>
<c:forEach items="${viewTeamMembers}" var="competitor">
    <c:out value= "${competitor.name}" /> ran:<c:out value= "${competitor.distanceRan}" /> swam:<c:out value= "${competitor.distanceSwam}" /> biked:<c:out value= "${competitor.distanceBiked}" />
    <a href="/admin/team/delete/${competitor.competitorId}">Delete Team Member</a>
    </br>
</c:forEach>
    <a href="/admin/team/delete/all${team.teamId}">Delete Entire Team</a>

</body>
</html>