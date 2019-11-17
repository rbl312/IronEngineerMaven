<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>

<body>
<a href="/admin/home">Home</a> <br />
<h2> All Teams!</h2>
<c:forEach items="${viewTeam}" var="team">
    <c:out value= "${team.teamName}" />
    <c:out value="${team.size}"/>
    <a href="/admin/team/view/${team.teamId}">View Team Members</a>
    </br>
</c:forEach>

<a href="/home">Home Page</a>

</body>
</html>