<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>

<body>
    <div>Create a Team</div>
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
</body>
</html>