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
            <h2> Logs to approve</h2>
        </form>
    </div>
</div>
<div class= "row text-center">
    <div class = "col">
        <c:forEach items="${approveLogs}" var="log">
            <form action="/admin/approve/log/${log.logId}">
                <c:out value= "${log.logId}" />
                </br>
                Distance Swam = <c:out value= "${log.distanceSwam}" />
                </br>
                Distance Biked = <c:out value= "${log.distanceBiked}" />
                </br>
                Distance Ran = <c:out value= "${log.distanceRan}" />
                </br>
                <button class = "button"> Approve this Log </button>
            </form>
            <form action="/admin/disapprove/log/${log.logId}">
                <button class = "button"> Disapprove this Log </button>
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