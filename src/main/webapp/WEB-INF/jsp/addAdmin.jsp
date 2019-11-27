<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href="/style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
<div>Add New Administrator</div>
<form:form method="POST"
           action="/admin/addadmin/promote" modelAttribute="newAdmin">
    <table>
        <tr>
            <td><form:label path="email">User Email</form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
<form action="/home">
    <button class="button">Home Page</button>
</form>
</body>
</html>