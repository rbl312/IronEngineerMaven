<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/bootstrap.css" rel="stylesheet">
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<div class="jumbotron text-center ">
    Open Teams
</div>

<c:forEach items="${joinableTeams}" var="team">
    <div class = "row text-center">
        <div class = "col">
            <form action="/team/join/${team.teamId}">
                <c:out value= "${team.teamName}" />
                <button class = "button">Join this team</button>
            </form>
        </div>
    </div>
</c:forEach>
</body>
</html>