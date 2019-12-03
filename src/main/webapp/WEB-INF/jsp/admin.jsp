<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="/bootstrap.css" rel="stylesheet">
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>

<div class="jumbotron text-center">
    Welcome <c:out value= "${name}"/>!
    </br>
    You are on the Admin page!
</div>
<div class= "row text-center">
    <div class = "col-sm">
        <form action="/admin/approve">
            <button class = "button">Approve team names</button>
        </form>
    </div>
    <div class = "col-sm">
            <form action="/admin/approve/log">
                <button class = "button">Approve Logs</button>
            </form>
        </div>
    <div class = "col-sm">
        <form action="/admin/team/view">
            <button class = "button">View All Team Progress</button>
        </form>
    </div>
    <div class = "col-sm">
        <form action="/admin/addadmin">
            <button class = "button">Promote Users to Admin</button>
        </form>
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
        <form action="/logout">
            <button class = "button">Logout</button>
        </form>
    </div>
</div>
</body>
</html>