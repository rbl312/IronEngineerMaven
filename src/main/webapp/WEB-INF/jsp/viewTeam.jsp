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
            <h2> All Teams</h2>
        </form>
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
        <c:forEach items="${viewTeam}" var="team">
            <form action="/admin/team/view/${team.teamId}">
                Name: <c:out value= "${team.teamName}" />
                Size: <c:out value="${team.getCompetitors().size()}"/>
                <button class = "button">View this team</button>
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