<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>
<h2>Approved Teams</h2>

<c:forEach items="${approvedTeams}" var="team">
    Team Name:<c:out value= "${team.teamName}" />
    Team Size:<c:out value="${team.size}" />
    <a href="/admin/team/view/${team.teamId}">View Team</a>
<%--    <a href="/admin/remove/team/${team.teamId}">Delete Team</a>--%>
</c:forEach>
<br>
<h2>Unapproved Teams</h2>

<c:forEach items ="${unapprovedTeams}" var="team">
    Team Name:<c:out value="${team.teamName}" />
    Team Size:<c:out value="${team.size}" />
    <a href="/admin/team/view/${team.teamId}">View Team</a>
<%--    <a href="/admin/remove/team/${team.teamId}">Delete Team</a>--%>
    <a href="/admin/approve/${team.teamId}">Approve Team</a>
</c:forEach>
<br><br><br><br><br><br><br><br><br><br>
<a href="/home">Home page</a>
</body>
</html>