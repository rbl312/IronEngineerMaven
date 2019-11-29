<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="/bootstrap.css" rel="stylesheet">
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<div class="jumbotron text-center ">
    Create a Team
</div>
<div class = "row text-center">
        <div class = "col">
             <form:form method="POST"
                action="/team/add" modelAttribute="newTeam">
                <table>
                    <tr>
                        <td><form:label path="teamName">Team Name</form:label></td>
                        <td><form:input path="teamName"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </table>
             </form:form>
        </div>
</div>
</body>
</html>