<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
    <div>Enter new log</div>
    <form:form method="POST"
              action="/addLog" modelAttribute="newLog">
                 <table>
                    <tr>
                        <td><form:label path="distanceSwam">Distance Swam (in Miles)</form:label></td>
                        <td><form:input path="distanceSwam"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="distanceBiked">Distance Biked (in Miles)</form:label></td>
                        <td><form:input path="distanceBiked"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="distanceRan">Distance Ran (in Miles)</form:label></td>
                        <td><form:input path="distanceRan"/></td>
                    </tr>
                    <tr>
                        <td><form:checkbox path="integrityCheckbox"/>
                        By checking this box you certify that you completed the distances specified during the race period
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </table>
     </form:form>

</body>
</html>