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
        <form action="/admin/approve">
            <h2> Team names to approve</h2>
        </form>
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
        <c:forEach items="${approveTeams}" var="team">
            <form action="/admin/approve/${team.teamId}">
                <c:out value= "${team.teamName}" />
                <button class = "button">Approve this team name</button>
            </form>
            </br>
        </c:forEach>
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
        <form action="/admin/home">
            <button class="button">Home Page</button>
        </form>
    </div>
</div>


</body>
</html>