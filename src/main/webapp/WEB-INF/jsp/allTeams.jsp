<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<h2>Approved Teams</h2>

<c:forEach items="${approvedTeams}" var="team">
    Team Name:<c:out value= "${team.teamName}" />
    Team Size:<c:out value="${team.getCompetitors().size()}" />
    <form action="/admin/team/view/${team.teamId}">
            <button class = "button">View Team</button>
    </form>
<%--    <a href="/admin/remove/team/${team.teamId}">Delete Team</a>--%>
</c:forEach>
<br>
<h2>Unapproved Teams</h2>

<c:forEach items ="${unapprovedTeams}" var="team">
    Team Name:<c:out value="${team.teamName}" />
    Team Size:<c:out value="${team.getCompetitors().size()}" />
    <form action="/admin/team/view/${team.teamId}">
        <button class = "button">View Team</button>
    </form>
<%--    <a href="/admin/remove/team/${team.teamId}">Delete Team</a>--%>
    <form action="/admin/approve/${team.teamId}">
         <button class = "button">Approve Team</button>
     </form>
</c:forEach>
</body>
</html>